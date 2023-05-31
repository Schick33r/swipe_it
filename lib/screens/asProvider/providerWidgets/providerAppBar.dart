import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class ProviderAppBar extends StatefulWidget {
  const ProviderAppBar({super.key});

  @override
  State<ProviderAppBar> createState() => _ProviderAppBarState();
}

class _ProviderAppBarState extends State<ProviderAppBar> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            IconButton(
                onPressed: () {},
                icon: Icon(
                  Icons.arrow_drop_down,
                  size: 38,
                  color: brownMainColor,
                )),
            Text('WG-ZIMMER STUTTGART',
                style: GoogleFonts.inter(
                    letterSpacing: 2,
                    fontSize: 16,
                    fontWeight: FontWeight.w600,
                    color: brownLightColor)),
            IconButton(
                onPressed: () {},
                icon: Icon(
                  Icons.filter_alt_outlined,
                  size: 32,
                  color: brownMainColor,
                )),
          ],
        ),
      ),
    );
  }
}
