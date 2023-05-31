import 'package:flutter/material.dart';
import 'package:swipe_it/global_widgets/chat_AppBar.dart';
import 'package:swipe_it/global_widgets/chat_bubble.dart';
import 'package:swipe_it/globals.dart';

class ChatScreen extends StatelessWidget {
  const ChatScreen({super.key});

  @override
  Widget build(BuildContext context) {
    var chatMessages = [
      {
        'name': 'David',
        'message': 'Message 1',
        'time': '17:32',
        'messageFromCurrentUser': false
      },
      {
        'name': 'Klaus',
        'message': 'Message 2',
        'time': '19:10',
        'messageFromCurrentUser': true
      },
      {
        'name': 'Klaus',
        'message': 'Message 3',
        'time': '19:10',
        'messageFromCurrentUser': true
      },
      {
        'name': 'Klaus',
        'message': 'Message 4',
        'time': '19:10',
        'messageFromCurrentUser': true
      },
      {
        'name': 'Klaus',
        'message': 'Message 5',
        'time': '19:10',
        'messageFromCurrentUser': true
      },
    ];

    var fromCurrentUser = [false, true, true, false, true];

    return Scaffold(
      backgroundColor: bgColor,
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const ChatAppBar(),
          Expanded(
            child: Container(
                color: bgColor,
                child: ListView.builder(
                    itemCount: 5,
                    itemBuilder: (BuildContext context, int index) {
                      return Center(
                          child: ChatBubble(
                              chatMessage:
                                  chatMessages[index]['message'].toString(),
                              messageFromCurrentUser: fromCurrentUser[index]));
                    })),
          ),

          /// MESSAGE BOX
          Container(
            color: brownLightColor,
            height: MediaQuery.of(context).size.height * 0.095,
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  SizedBox(
                      width: MediaQuery.of(context).size.width * 0.84,
                      height: MediaQuery.of(context).size.height * 0.04,
                      child: TextField(
                        //controller: _firstNameController,
                        style: const TextStyle(
                          color: Colors.white,
                        ),
                        decoration: InputDecoration(
                          enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: brownLightColor),
                              borderRadius: const BorderRadius.only(
                                  topLeft: Radius.circular(8),
                                  bottomLeft: Radius.circular(8))),

                          focusedBorder: const OutlineInputBorder(
                              borderSide: BorderSide.none),
                          // focusedBorder: OutlineInputBorder(
                          //     borderSide: BorderSide(color: brownDarkest),
                          //     borderRadius: const BorderRadius.only(
                          //         topLeft: Radius.circular(8),
                          //         bottomLeft: Radius.circular(8))),
                          hintText: 'Write message to send..',
                          hintStyle: const TextStyle(color: Colors.white),
                          fillColor: cardBg,
                          filled: true,
                        ),
                      )),

                  // SEND BUTTON
                  Expanded(
                    child: GestureDetector(
                      onTap: () async {},
                      child: Container(
                          decoration: BoxDecoration(
                              color: brownMainColor,
                              borderRadius: const BorderRadius.only(
                                  topRight: Radius.circular(8),
                                  bottomRight: Radius.circular(8))),
                          alignment: Alignment.center,
                          //width: MediaQuery.of(context).size.width * 0.1,
                          height: MediaQuery.of(context).size.height * 0.04,
                          child: const Icon(
                            Icons.send,
                            size: 20,
                            color: Colors.white,
                          )),
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
