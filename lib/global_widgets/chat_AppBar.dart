import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class ChatAppBar extends StatelessWidget {
  const ChatAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height * 0.11,
      color: brownLightColor,
      child: Padding(
        padding: const EdgeInsets.only(top: 30.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            IconButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                icon: Icon(
                  Icons.arrow_back_ios,
                  color: whiteColor,
                  size: 24,
                )),
            const SizedBox(width: 10),

            // PROFILE PICTURE
            Container(
              height: 45,
              width: 45,
              decoration: BoxDecoration(
                  color: Colors.blue, borderRadius: BorderRadius.circular(50)),
            ),
            const SizedBox(width: 15),

            // NAME
            Text(
              'David',
              style: GoogleFonts.inter(
                  fontSize: 16, fontWeight: FontWeight.w500, color: whiteColor),
            ),

            SizedBox(width: MediaQuery.of(context).size.width * 0.45),

            // MORE BUTTON
            IconButton(
              onPressed: () {},
              icon: Icon(Icons.more_horiz),
              iconSize: 28,
              color: whiteColor,
            )
          ],
        ),
      ),
    );
  }
}
