package dev.tberghuis.btmacrokb.tmp
//
//import dev.tberghuis.btmacrokb.service.MyBtService
//import io.ktor.server.application.call
//import io.ktor.server.engine.embeddedServer
//import io.ktor.server.netty.Netty
//import io.ktor.server.response.respondText
//import io.ktor.server.routing.get
//import io.ktor.server.routing.routing
//
//fun runKtorServer(service: MyBtService) {
//  embeddedServer(Netty, port = 8080) {
//    routing {
//      get("/") {
//        call.respondText("Hello, world!\n")
//      }
//
//      get("/run_test") {
////        val s = "abcdefghijklmnopqrstuvwxyz"
////        val s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
////        val s = "Hello with Spaces"
////        val s = "Hello\twith\t\ttabs"
////        val s = "1234567890"
////        val s = "` - ="
////        val s = "[ ] \\"
////        val s = "; ' , . /"
////        val s = "~ ! @ # $ % ^ & * ( ) _ +"
//        val s = "{ } | : \" < > ?"
//
////        service.myBtController.getAdapter()
////        service.myBtController.getDevices()
////        val bd = service.myBtController.device!!
////        service.myBtController.getProfileProxy2()
////        service.myBtController.registerApp()
////        service.myBtController.connect()
////        service.myBtController.sendString(bd, s)
//        call.respondText("ok\n")
//      }
//    }
//  }.start(wait = true)
//}