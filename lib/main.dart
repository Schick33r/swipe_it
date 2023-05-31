import 'package:flutter/material.dart';
import 'package:swipe_it/screens/asProvider/provider_wrapper.dart';
import 'package:swipe_it/screens/swipe_test.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    var _currentIndex = 0;
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: // const AuthPage());
            const ProviderWrapperScreen());

    //MyHomePage());

    //  const ChatScreen());
  }
}
