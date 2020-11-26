package ru.naumov.spring.server

// Variables file
// There are constants and other variables (URIs, Classes etc.)


//defineDriver
val options = listOf("disable-infobars", "ignore-certificate-errors", "start-maximized","use-fake-ui-for-media-stream")
const val property = "webdriver.chrome.driver"
const val pathToDriver = "C:/ChromeDriver/chromedriver.exe"


//first page
const val connectButtonClassName = "primary"


//second page
const val permissionsClassName = "ts-toggle-button-container"
const val usernameClassName = "username"
const val startButtonClassName = "ts-btn-primary"


//third page
const val chatButtonClassName = "chat-button"
const val chatTextFieldXPath = "//*[@id=\"cke_1_contents\"]/div"
const val membersId = "vs-repeat-repeated-element"
const val chatSendMessageButtonId = "send-message-button"
val welcomePhrase = listOf("Здравствуйте!", "Добрый день", "Извините за опоздание, мос ру опять лагает")


//parseLink
const val ura = "https://teams.microsoft.com/dl/launcher/launcher.html?url=%2F_%23%2Fl%2F"
const val urb = "&type=meetup-join&deeplinkId="
const val urc = "&directDl=true&msLaunch=true&enableMobilePage=true&suppressPrompt=true"

//link example: http://localhost:8080/connect?name=Denis&mode=meet&url=meetup-join%2F19%3Ameeting_YWQ1ZGUyNzItY2NiYi00MTNjLWE4NTgtY2VhNjMxMTE0ZTli%40thread.v2%2F0%3Fcontext%3D%257b%2522Tid%2522%253a%2522dcb67d46-35b2-47d7-a41c-5507eb550715%2522%252c%2522Oid%2522%253a%25225f66540d-66cd-4980-9e04-7859066f90e5%2522%257d%26anon%3Dtrue&deepLinkId=b32e543f-6371-4f7b-89d5-754f93b48ed8

