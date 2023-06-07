import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:swipe_it/globals.dart';

class ProviderAppBar extends StatefulWidget {
  List<String> items;

  ProviderAppBar({super.key, required this.items});

  @override
  State<ProviderAppBar> createState() => _ProviderAppBarState();
}

class _ProviderAppBarState extends State<ProviderAppBar> {
  late String selectedItem;

  @override
  void initState() {
    super.initState();
    selectedItem = widget.items.first;
  }

  PopupMenuItem<String> _buildPopupMenuItem(String item) {
    return PopupMenuItem<String>(
        value: item,
        child: Container(
          child: Text(item,
              style: GoogleFonts.inter(
                  letterSpacing: 2,
                  fontSize: 16,
                  fontWeight: FontWeight.w600,
                  color: brownLightColor)),
        ));
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            PopupMenuButton<String>(
                color: bgColor,
                icon: Icon(
                  Icons.arrow_drop_down,
                  size: 38,
                  color: brownMainColor,
                ),
                initialValue: selectedItem,
                // Callback that sets the selected popup menu item.
                onSelected: (String item) {
                  setState(() {
                    selectedItem = item;
                  });
                },
                itemBuilder: (BuildContext context) => widget.items
                    .map((item) => _buildPopupMenuItem(item))
                    .toList()),
            Text(selectedItem,
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
