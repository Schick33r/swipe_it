import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:swipe_it/globals.dart';
import 'forgot_pw_page.dart';

class LoginPage extends StatefulWidget {
  final VoidCallback showRegisterPage;

  const LoginPage({Key? key, required this.showRegisterPage}) : super(key: key);
  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  Future signIn() async {
    await FirebaseAuth.instance.signInWithEmailAndPassword(
      email: _emailController.text.trim(),
      password: _passwordController.text.trim(),
    );
  }

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: bgColor,
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              const SizedBox(height: 35),
              // BARBOTZ
              SvgPicture.asset(
                'assets/mz_icon_brown.svg',
                width: 150,
                fit: BoxFit.contain,
              ),
              Text('FLUTTER LOGIN',
                  style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w500,
                      letterSpacing: 2,
                      color: whiteLightColor)),
              const SizedBox(height: 50),

              // Text(
              //   'Hello Again!',
              //   style: GoogleFonts.bebasNeue(
              //     color: Colors.deepOrange[200],
              //     fontSize: 36,
              //   ),
              // ),

              const SizedBox(height: 10),

              Text(
                'Welcome back, please sign in!',
                style: TextStyle(
                  color: whiteColor,
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

                      focusedBorder:
                          const OutlineInputBorder(borderSide: BorderSide.none),
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

              const SizedBox(height: 15),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 25.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) {
                              return const ForgotPasswordPage();
                            },
                          ),
                        );
                      },
                      child: Text(
                        'Forgot Password?',
                        style: TextStyle(
                            color: whiteLightColor,
                            fontSize: 13,
                            fontWeight: FontWeight.w700),
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 25),

              // sign in Button
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 25.0),
                child: GestureDetector(
                  onTap: signIn,
                  child: Container(
                    padding: const EdgeInsets.all(20.0),
                    decoration: BoxDecoration(
                      color: brownMainColor,
                      borderRadius: BorderRadius.circular(4.0),
                    ),
                    child: Center(
                        child: Text(
                      'Sign In',
                      style: TextStyle(
                          color: whiteColor,
                          fontSize: 16,
                          fontWeight: FontWeight.w700),
                    )),
                  ),
                ),
              ),

              const SizedBox(height: 30),

              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 25.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Expanded(
                      child: Container(height: 1, color: whiteColor),
                    ),
                    Text(
                      '    Or continue with    ',
                      style: GoogleFonts.inter(
                          fontSize: 14,
                          fontWeight: FontWeight.normal,
                          color: whiteColor),
                    ),
                    Expanded(
                      child: Container(height: 1, color: whiteColor),
                    )
                  ],
                ),
              ),
              const SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    width: 80,
                    height: 80,
                    decoration: BoxDecoration(
                        border: Border.all(width: 1, color: whiteColor),
                        borderRadius: BorderRadius.circular(4)),
                    child: Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: Image.asset(
                        'assets/apple_logo.png',
                      ),
                    ),
                  ),
                  const SizedBox(width: 20),
                  Container(
                    width: 80,
                    height: 80,
                    decoration: BoxDecoration(
                        border: Border.all(width: 1, color: whiteColor),
                        borderRadius: BorderRadius.circular(4)),
                    child: Padding(
                      padding: const EdgeInsets.all(9.0),
                      child: SvgPicture.asset(
                        'assets/google_logo.svg',
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 30),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    'Not a member?  ',
                    style: TextStyle(
                        color: Colors.grey[200],
                        fontSize: 14,
                        fontWeight: FontWeight.bold),
                  ),
                  GestureDetector(
                    onTap: widget.showRegisterPage,
                    child: Text(
                      'Register now',
                      style: TextStyle(
                          color: brownMainColor,
                          fontSize: 14,
                          fontWeight: FontWeight.bold),
                    ),
                  )
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
