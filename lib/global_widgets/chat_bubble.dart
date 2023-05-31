import 'package:flutter/material.dart';
import 'package:swipe_it/globals.dart';

class ChatBubble extends StatelessWidget {
  final bool messageFromCurrentUser;
  final String chatMessage;
  const ChatBubble(
      {super.key,
      required this.messageFromCurrentUser,
      required this.chatMessage});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: messageFromCurrentUser

          // MESSAGE FROM CURRENT USER --> RIGHT
          ? Align(
              alignment: Alignment.topRight,
              child: Container(
                decoration: BoxDecoration(
                    color: cardBg, borderRadius: BorderRadius.circular(8)),
                height: 50,
                width: 200,
                child: Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: Stack(
                    children: [
                      Text(
                        chatMessage,
                        style: TextStyle(fontSize: 15, color: whiteColor),
                      ),
                      Positioned(
                        right: 0,
                        bottom: 0,
                        child: Text(
                          '16:32',
                          style: TextStyle(fontSize: 12, color: whiteColor),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            )
          // MESSAGE FROM OTHER USER ----> LEFT
          : Align(
              alignment: Alignment.topLeft,
              child: Container(
                decoration: BoxDecoration(
                    color: cardBg, borderRadius: BorderRadius.circular(8)),
                height: 50,
                width: 200,
                child: Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: Stack(
                    children: [
                      Text(
                        chatMessage,
                        style: TextStyle(fontSize: 15, color: whiteColor),
                      ),
                      Positioned(
                        right: 0,
                        bottom: 0,
                        child: Text(
                          '16:32',
                          style: TextStyle(fontSize: 12, color: whiteColor),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
    );
  }
}
