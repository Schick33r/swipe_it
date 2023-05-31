import 'package:flutter/material.dart';
import 'package:swipe_it/globals.dart';

class ProviderSwipeScreen extends StatelessWidget {
  const ProviderSwipeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.only(top: 15.0),
        child: Container(
          color: bgColor,
          // height: MediaQuery.of(context).size.height * 0,
          width: double.infinity,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15.0),
            child: Column(
              children: [
                /// AD CARD
                Container(
                    decoration: BoxDecoration(
                        color: brownMainColor,
                        borderRadius: BorderRadius.circular(25)),
                    height: MediaQuery.of(context).size.height * 0.68),

                const SizedBox(height: 12),

                /// AD BUTTONS
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    /// UNDO BUTTON
                    GestureDetector(
                      onTap: () {},
                      child: Container(
                        height: 60,
                        width: 60,
                        decoration: BoxDecoration(
                            color: brownLightest,
                            borderRadius: BorderRadius.circular(50)),
                        child: Icon(
                          Icons.undo,
                          size: 28,
                          color: brownMainColor,
                        ),
                      ),
                    ),

                    /// LIKE BUTTON
                    GestureDetector(
                      onTap: () {},
                      child: Container(
                        height: 60,
                        width: 60,
                        decoration: BoxDecoration(
                            color: brownLightest,
                            borderRadius: BorderRadius.circular(50)),
                        child: Icon(
                          Icons.check,
                          size: 32,
                          color: Colors.green.shade700,
                        ),
                      ),
                    ),

                    /// LIKE BUTTON
                    GestureDetector(
                      onTap: () {},
                      child: Container(
                        height: 60,
                        width: 60,
                        decoration: BoxDecoration(
                            color: brownLightest,
                            borderRadius: BorderRadius.circular(50)),
                        child: Icon(
                          Icons.close,
                          size: 32,
                          color: Colors.red.shade500,
                        ),
                      ),
                    )
                  ],
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
