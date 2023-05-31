import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class RegisterPage extends StatefulWidget {
  final VoidCallback showLoginPage;

  const RegisterPage({Key? key, required this.showLoginPage}) : super(key: key);

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  // text controller
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  final _firstNameController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    _firstNameController.dispose();
    super.dispose();
  }

  Future signUp() async {
    // create user with email and password
    if (passwordConfirmed()) {
      await FirebaseAuth.instance.createUserWithEmailAndPassword(
        email: _emailController.text.trim(),
        password: _passwordController.text.trim(),
      );
      // add user details
      addUserDeatails(
        _firstNameController.text.trim(),
        _emailController.text.trim(),
      );
    }
  }

  Future addUserDeatails(String firstname, String email) async {
    await FirebaseFirestore.instance.collection('users').add({
      'first name': firstname,
      'email': email,
    });
  }

  bool passwordConfirmed() {
    if (_passwordController.text.trim() ==
        _confirmPasswordController.text.trim()) {
      return true;
    } else {
      return false;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: bgColor,
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              children: [
                SizedBox(height: 35),

                SvgPicture.asset(
                  'assets/mz_icon_brown.svg',
                  width: 150,
                ),
                Text('FLUTTER LOGIN',
                    style: GoogleFonts.inter(
                        fontSize: 12,
                        fontWeight: FontWeight.w500,
                        letterSpacing: 2,
                        color: whiteLightColor)),

                const SizedBox(height: 60),

                Text(
                  'Hello There!',
                  style: GoogleFonts.bebasNeue(
                    color: brownMainColor,
                    fontSize: 36,
                  ),
                ),

                const SizedBox(height: 10),

                Text(
                  'Register below with your details!',
                  style: TextStyle(
                    color: whiteColor,
                    fontSize: 19.0,
                  ),
                ),

                const SizedBox(height: 40),

                // first name textfield
                Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 25.0),
                    child: TextField(
                      controller: _firstNameController,
                      style: const TextStyle(
                        color: Colors.white,
                      ),
                      decoration: InputDecoration(
                        enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: textFieldColor),
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
                        hintText: 'First Name',
                        hintStyle: const TextStyle(color: Colors.white),
                        fillColor: textFieldColor,
                        filled: true,
                      ),
                    )),
                const SizedBox(height: 10),

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

                        focusedBorder: const OutlineInputBorder(
                            borderSide: BorderSide.none),
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
                const SizedBox(height: 10),
                // password textfield
                Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 25.0),
                    child: TextField(
                      controller: _passwordController,
                      style: const TextStyle(
                        color: Colors.white,
                      ),
                      decoration: InputDecoration(
                        enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: textFieldColor),
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
                        hintText: 'Password',
                        hintStyle: const TextStyle(color: Colors.white),
                        fillColor: textFieldColor,
                        filled: true,
                      ),
                    )),
                const SizedBox(height: 10),

                // password textfield
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

                        focusedBorder: const OutlineInputBorder(
                            borderSide: BorderSide.none),
                        // focusedBorder: OutlineInputBorder(
                        //     borderSide: BorderSide(color: brownDarkest),
                        //     borderRadius: const BorderRadius.only(
                        //         topLeft: Radius.circular(8),
                        //         bottomLeft: Radius.circular(8))),
                        hintText: 'Confirm Password',
                        hintStyle: const TextStyle(color: Colors.white),
                        fillColor: textFieldColor,
                        filled: true,
                      ),
                    )),
                const SizedBox(height: 10),

                // sign in Button
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 25.0),
                  child: GestureDetector(
                    onTap: signUp,
                    child: Container(
                      padding: EdgeInsets.all(20.0),
                      decoration: BoxDecoration(
                        color: brownMainColor,
                        borderRadius: BorderRadius.circular(4.0),
                      ),
                      child: Center(
                          child: Text(
                        'Register',
                        style: TextStyle(
                            color: whiteColor,
                            fontSize: 14,
                            fontWeight: FontWeight.w700),
                      )),
                    ),
                  ),
                ),

                const SizedBox(height: 30),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      'I am a member!  ',
                      style: TextStyle(
                          color: Colors.grey[200],
                          fontSize: 14,
                          fontWeight: FontWeight.bold),
                    ),
                    GestureDetector(
                      onTap: widget.showLoginPage,
                      child: Text(
                        'Sign In',
                        style: TextStyle(
                            color: brownMainColor,
                            fontSize: 14,
                            fontWeight: FontWeight.bold),
                      ),
                    )
                  ],
                ),
                const SizedBox(height: 250),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
