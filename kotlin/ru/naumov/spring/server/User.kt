package ru.naumov.spring.server

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class User: Runnable {

    private var name: String
    private var link: String
    private var isRunning: Boolean

    constructor(link: String, name: String){
        this.link = link
        this.name = name
        this.isRunning = true
    }

    override fun run(){
        val driver = defineDriver()

        // Load discussion page
        driver.get(link)
        val connectButton: WebElement = driver.findElements(By.className(connectButtonClassName))[1]
        connectButton.click()

        //load second page
        while(!waitForPreview(driver)){
            Thread.sleep(1000)
        }
        Thread.sleep(5000)
        val perms = driver.findElements(By.className(permissionsClassName))
        val username = driver.findElement(By.id(usernameClassName))
        Thread.sleep(5000)
        username.sendKeys(name)
        perms.forEach { it.click() }
        val startButton = driver.findElement(By.className(startButtonClassName))
        startButton.click()


        //Something with third page
        while (waitForTaking(driver)){
            Thread.sleep(1000)
        }

        Thread.sleep(3000)
        sendMessage(driver, welcomePhrase[1])

        var members = countMembers(driver)
        var dif: Int

        while (true) {
            Thread.sleep(5000)
            dif = members - countMembers(driver)
            //if (dif >= 3 || members - dif <= 4){
            //    driver.close()
            //    break
            //} else {
            //    members -= dif
                println("Members: $members")
                println("Left: $dif")
                println()
            //}
        }

    }


    private fun defineDriver(): ChromeDriver {
        val opt = ChromeOptions()
        //opt.setBinary(System.getenv("GOOGLE_CHROME_BIN"))
        opt.addArguments(options)
        //System.setProperty(property, System.getenv("CHROMEDRIVER_PATH"))
        System.setProperty(property, pathToDriver)
        return ChromeDriver(opt)
    }

    private fun waitForTaking(driver: ChromeDriver): Boolean {
        return try {
            driver.findElement(By.className("controls-container"))
            println(" $name     Is waiting for taking...")
            true
        } catch (e: Exception){
            println(" $name     Entered to meeting...")
            false
        }
    }

    private fun countMembers(driver: ChromeDriver): Int {
        val membersButton = driver.findElement(By.id("roster-button"))
        membersButton.click()
        val members = driver.findElements(By.id(membersId))
        return members.size
    }

    private fun waitForPreview(driver: ChromeDriver): Boolean {
        return try {
            driver.findElements(By.className(permissionsClassName))
            println(" $name     Entered to preview...")
            true
        } catch (e: Exception){
            println(" $name     Is waiting for preview...")
            false

        }
    }

    private fun sendMessage(driver: ChromeDriver, message: String): Boolean {
        return try {
            val chat = driver.findElement(By.id(chatButtonClassName))
            chat.click()
            val chatTextField = driver.findElement(By.xpath(chatTextFieldXPath))
            val chatSendMessageButton = driver.findElement(By.id(chatSendMessageButtonId))
            chatTextField.sendKeys(message)
            chatSendMessageButton.click()
            true
        } catch (e: Exception){
            println("Chat disabled")
            false
        }
    }

    override fun toString(): String {
        return "$name $link"
    }

}