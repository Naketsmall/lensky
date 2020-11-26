package ru.naumov.spring.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.naumov.spring.server.User
import ru.naumov.spring.server.ura
import ru.naumov.spring.server.urb
import ru.naumov.spring.server.urc

@Controller
class FirstController{

    @GetMapping("/connect")
    fun connectPage(@RequestParam("name") name: String,
                    @RequestParam("url") url: String, @RequestParam("deepLinkId") deepLinkId: String,
                    model: Model): String {
        println(url)
        val link = createLink(url, deepLinkId)
        val requestInfo = "Name: $name\n" +
                "Link: $link"
        println(requestInfo)
        println(link)
        model.addAttribute("requestInfo", requestInfo)
        Thread(User(link, name)).start()
        return "first/connect"
    }

    private fun createLink(url: String, deepLinkId: String): String {
        if (url.contains("/")) url.replace("/", "%")
        return StringBuffer().append(ura + url + urb + deepLinkId + urc).toString()
    }

}