import 'package:flutter/material.dart';
import 'package:swipe_it/global_widgets/chat_AppBar.dart';
import 'package:swipe_it/global_widgets/chat_bubble.dart';
import 'package:swipe_it/globals.dart';

class ChatScreen extends StatefulWidget {
  const ChatScreen({super.key});

  @override
  State<StatefulWidget> createState() => ChatScreenState();
}

class ChatScreenState extends State<ChatScreen> {
  final TextEditingController _chatMessageController = TextEditingController();

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

  void _addMessageToList(String message, bool messageFromCurrentUser) {
    setState(() {
      chatMessages.add({
        'name': 'Klaus',
        'message': message,
        'time': '19:10',
        'messageFromCurrentUser': messageFromCurrentUser
      });
    });

    _chatMessageController.clear();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: () {
          // Close the keyboard when tapping outside the TextField
          FocusScope.of(context).requestFocus(FocusNode());
        },
        child: Scaffold(
          backgroundColor: bgColor,
          body: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const ChatAppBar(),
              Expanded(
                child: Container(
                    color: bgColor,
                    child: ListView.builder(
                        itemCount: chatMessages.length,
                        itemBuilder: (BuildContext context, int index) {
                          return Center(
                              child: ChatBubble(
                                  chatMessage:
                                      chatMessages[index]['message'].toString(),
                                  messageFromCurrentUser: chatMessages[index]
                                      ['messageFromCurrentUser'] as bool));
                        })),
              ),

              /// MESSAGE BOX
              Container(
                color: brownLightColor,
                height: MediaQuery.of(context).size.height * 0.095,
                child: Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      SizedBox(
                          width: MediaQuery.of(context).size.width * 0.84,
                          height: MediaQuery.of(context).size.height * 0.04,
                          child: TextField(
                            controller: _chatMessageController,
                            textAlignVertical: TextAlignVertical.bottom,
                            //controller: _firstNameController,
                            style: const TextStyle(
                              color: Colors.white,
                            ),
                            decoration: InputDecoration(
                              enabledBorder: OutlineInputBorder(
                                  borderSide:
                                      BorderSide(color: brownLightColor),
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
                          onTap: () => _addMessageToList(
                              _chatMessageController.text, true),
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
        ));
  }
}
