import 'package:flutter/material.dart';
import 'package:swipe_it/global_widgets/chat_card.dart';

class ProviderChats extends StatelessWidget {
  const ProviderChats({super.key});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.only(top: 15.0),
        child: ListView.builder(
            itemCount: 5,
            itemBuilder: (BuildContext context, int index) {
              return const ChatCard();
            }),
      ),
    );
  }
}
