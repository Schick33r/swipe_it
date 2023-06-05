import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class ForgotPasswordPage extends StatefulWidget {
  const ForgotPasswordPage({Key? key}) : super(key: key);

  @override
  State<ForgotPasswordPage> createState() => _ForgotPasswordPageState();
}

class _ForgotPasswordPageState extends State<ForgotPasswordPage> {
  final _emailController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    super.dispose();
  }

  Future passwordReset() async {
    try {
      await FirebaseAuth.instance
          .sendPasswordResetEmail(email: _emailController.text.trim());
      showDialog(
          context: context,
          builder: (context) {
            return const AlertDialog(
              content: Text('Password reset link sent! Check your email!'),
            );
          }).then((value) => Navigator.pop(context));
    } on FirebaseAuthException catch (e) {
      print(e);
      showDialog(
          context: context,
          builder: (context) {
            return AlertDialog(
              content: Text(e.message.toString()),
            );
          });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: bgColor,
        appBar: AppBar(
          elevation: 0,
          backgroundColor: bgColor,
        ),
        body: Column(
          children: [
            SvgPicture.asset('assets/mz_icon_brown.svg', width: 150),
            const SizedBox(height: 100),

            Text(
              'Enter Your Email',
              style: GoogleFonts.bebasNeue(
                color: whiteColor,
                fontSize: 36,
              ),
            ),

            const SizedBox(height: 10),

            const Text(
              'and we send you a password reset link',
              style: TextStyle(
                color: Color.fromARGB(213, 255, 255, 255),
                fontSize: 19.0,
              ),
            ),

            const SizedBox(height: 40),

            // email textfield
            Padding(
                padding: const EdgeInsets.symmetric(horizontal: 25.0),
                child: TextField(
                  controller: _emailController,
                  style: const TextStyle(
                    color: Colors.white,
                  ),
                  decoration: InputDecoration(
                    enabledBorder: OutlineInputBorder(
                        borderSide: BorderSide(color: textFieldColor),
                        borderRadius: const BorderRadius.only(
                            topLeft: Radius.circular(8),
                            bottomLeft: Radius.circular(8))),

                    focusedBorder:
                        const OutlineInputBorder(borderSide: BorderSide.none),
                    // focusedBorder: OutlineInputBorder(
                    //     borderSide: BorderSide(color: brownDarkest),
                    //     borderRadius: const BorderRadius.only(
                    //         topLeft: Radius.circular(8),
                    //         bottomLeft: Radius.circular(8))),
                    hintText: 'Email',
                    hintStyle: const TextStyle(color: Colors.white),
                    fillColor: textFieldColor,
                    filled: true,
                  ),
                )),

            const SizedBox(height: 40),

            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 25.0),
              child: GestureDetector(
                onTap: passwordReset,
                child: Container(
                  padding: const EdgeInsets.all(20.0),
                  decoration: BoxDecoration(
                    color: brownMainColor,
                    borderRadius: BorderRadius.circular(4.0),
                  ),
                  child: Center(
                      child: Text(
                    'Reset Password',
                    style: TextStyle(
                        color: whiteColor,
                        fontSize: 15,
                        fontWeight: FontWeight.w700),
                  )),
                ),
              ),
            ),
          ],
        ));
  }
}
