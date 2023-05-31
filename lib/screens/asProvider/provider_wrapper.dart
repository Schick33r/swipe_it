import 'package:flutter/material.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';
import 'package:swipe_it/globals.dart';
import 'package:swipe_it/screens/asProvider/providerWidgets/providerAppBar.dart';
import 'package:swipe_it/screens/asProvider/provider_Chats.dart';
import 'package:swipe_it/screens/asProvider/provider_swipe.dart';
import 'package:swipe_it/screens/chat_screen.dart';
import 'package:swipe_it/screens/profile_screen.dart';

class ProviderWrapperScreen extends StatefulWidget {
  const ProviderWrapperScreen({super.key});

  @override
  State<ProviderWrapperScreen> createState() => _ProviderWrapperScreenState();
}

class _ProviderWrapperScreenState extends State<ProviderWrapperScreen> {
  var _currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    buildView() {
      if (_currentIndex == 0) {
        return //const Text('SwipeScreen');

            const ProviderSwipeScreen();
      }
      if (_currentIndex == 1) {
        return const Text('Search Screen');
      }
      if (_currentIndex == 2) {
        return //const Text('Chat Screen');
            const ProviderChats();
      }
      if (_currentIndex == 3) {
        return const ProfileScreen();
      } else {
        const Text('Error');
      }
    }

    return Scaffold(
      bottomNavigationBar: SalomonBottomBar(
        currentIndex: _currentIndex,
        //onTap: (i) => setState(() => _currentIndex = i),

        onTap: (i) {
          setState(() {
            if (i == 3) {
              print('isProfile');
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const ProfileScreen()),
              );
            } else {
              _currentIndex = i;
            }
          });
        },

        items: [
          /// Home
          SalomonBottomBarItem(
            icon: const Icon(Icons.home),
            title: const Text("Swipe"),
            selectedColor: Colors.purple,
          ),

          /// Search
          SalomonBottomBarItem(
            icon: const Icon(Icons.search),
            title: const Text("Search"),
            selectedColor: Colors.orange,
          ),

          /// MESSAGES
          SalomonBottomBarItem(
            icon: const Icon(Icons.message_rounded),
            title: const Text("Messages"),
            selectedColor: Colors.pink,
          ),

          /// Profile
          SalomonBottomBarItem(
            icon: const Icon(Icons.person),
            title: const Text("Profile"),
            selectedColor: Colors.teal,
          ),
        ],
      ),
      backgroundColor: bgColor,
      body: SafeArea(
        child: Column(
          children: [
            const ProviderAppBar(),

            buildView()!
            //const Text('ProviderWrappeScreen')
            // ProviderSwipeScreen()
            // ProviderChats()
            //const ChatScreen()
          ],
        ),
      ),
    );
  }
}
