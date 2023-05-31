import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: bgColor,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.only(top: 15),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  // BACK BUTTON
                  Padding(
                    padding: const EdgeInsets.only(bottom: 120.0),
                    child: IconButton(
                        onPressed: () {
                          Navigator.pop(context);
                        },
                        icon: Icon(
                          Icons.arrow_back_ios,
                          color: brownMainColor,
                          size: 30,
                        )),
                  ),

                  Column(
                    children: [
                      /// PROFILE PICTURE
                      Container(
                        height: 150,
                        width: 150,
                        decoration: BoxDecoration(
                            color: Colors.blue,
                            borderRadius: BorderRadius.circular(75)),
                      ),
                      const SizedBox(height: 10),

                      /// NAME & AGE
                      Text(
                        'Jasmin, 29',
                        style: GoogleFonts.inter(
                            fontSize: 26,
                            fontWeight: FontWeight.w600,
                            color: brownMainColor),
                      ),
                    ],
                  ),
                  // DUMMY BUTTON FOR SYMETRY
                  IconButton(
                      onPressed: () {},
                      icon: const Icon(
                        Icons.arrow_back_ios,
                        color: Colors.transparent,
                      ))
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  /// SETTINGS
                  Column(
                    children: [
                      Container(
                          width: 65,
                          height: 65,
                          decoration: BoxDecoration(
                              color: brownLightest,
                              borderRadius: BorderRadius.circular(50)),
                          child: Icon(Icons.settings,
                              size: 42, color: whiteLightColor)),
                      const SizedBox(height: 15),
                      Text(
                        'SETTINGS',
                        style: GoogleFonts.inter(
                            fontSize: 14,
                            letterSpacing: 1.2,
                            fontWeight: FontWeight.w600,
                            color: brownLightest),
                      ),
                    ],
                  ),

                  /// ADD MEDIA
                  Padding(
                    padding: const EdgeInsets.only(top: 50.0),
                    child: Column(
                      children: [
                        Container(
                            width: 75,
                            height: 75,
                            decoration: BoxDecoration(
                                color: brownLightest,
                                borderRadius: BorderRadius.circular(50)),
                            child: Icon(Icons.add_a_photo,
                                size: 42, color: whiteLightColor)),
                        const SizedBox(height: 15),
                        Text(
                          'ADD MEDIA',
                          style: GoogleFonts.inter(
                              fontSize: 14,
                              letterSpacing: 1.2,
                              fontWeight: FontWeight.w600,
                              color: brownLightest),
                        ),
                      ],
                    ),
                  ),

                  /// EDIT INFO
                  Column(
                    children: [
                      Container(
                          height: 65,
                          width: 65,
                          decoration: BoxDecoration(
                              color: brownLightest,
                              borderRadius: BorderRadius.circular(50)),
                          child: Icon(Icons.edit,
                              size: 42, color: whiteLightColor)),
                      const SizedBox(height: 15),
                      Text(
                        'EDIT INFO',
                        style: GoogleFonts.inter(
                            fontSize: 14,
                            letterSpacing: 1.2,
                            fontWeight: FontWeight.w600,
                            color: brownLightest),
                      ),
                    ],
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
