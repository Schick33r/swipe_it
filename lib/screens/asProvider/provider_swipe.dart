import 'package:flutter/material.dart';
import 'package:swipe_cards/draggable_card.dart';
import 'package:swipe_it/globals.dart';
import 'package:swipe_cards/swipe_cards.dart';
import 'package:swipe_it/screens/provider_swipe_content_card.dart';

class ProviderSwipeScreen extends StatefulWidget {
  const ProviderSwipeScreen({super.key});

  @override
  State<ProviderSwipeScreen> createState() => _ProviderSwipeScreenState();
}

class _ProviderSwipeScreenState extends State<ProviderSwipeScreen> {
  final List<SwipeItem> _swipeItems = <SwipeItem>[];
  MatchEngine? _matchEngine;
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();
  final List<String> _names = [
    "Red",
    "Blue",
    "Green",
    "Yellow",
    "Orange",
    "Grey",
    "Purple",
    "Pink"
  ];
  final List<Color> _colors = [
    Colors.red,
    Colors.blue,
    Colors.green,
    Colors.yellow,
    Colors.orange,
    Colors.grey,
    Colors.purple,
    Colors.pink
  ];

  @override
  void initState() {
    for (int i = 0; i < _names.length; i++) {
      _swipeItems.add(SwipeItem(
          content: ProviderSwipeContentCard(text: _names[i], color: _colors[i]),
          likeAction: () {
            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
              content: Text("Liked ${_names[i]}"),
              duration: const Duration(milliseconds: 500),
            ));
          },
          nopeAction: () {
            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
              content: Text("Nope ${_names[i]}"),
              duration: const Duration(milliseconds: 500),
            ));
          },
          superlikeAction: () {
            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
              content: Text("Superliked ${_names[i]}"),
              duration: const Duration(milliseconds: 500),
            ));
          },
          onSlideUpdate: (SlideRegion? region) async {
            print("Region $region");
          }));
    }

    _matchEngine = MatchEngine(swipeItems: _swipeItems);
    super.initState();
  }

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
                  height: MediaQuery.of(context).size.height * 0.68,

                  /// SWIPE CARDS
                  child: SwipeCards(
                    matchEngine: _matchEngine!,
                    itemBuilder: (BuildContext context, int index) {
                      return Container(
                        decoration: BoxDecoration(
                            color: _swipeItems[index].content.color,
                            borderRadius: BorderRadius.circular(25)),
                        alignment: Alignment.center,
                        child: Text(
                          _swipeItems[index].content.text,
                          style: const TextStyle(fontSize: 100),
                        ),
                      );
                    },
                    onStackFinished: () {
                      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                        content: Text("Stack Finished"),
                        duration: Duration(milliseconds: 500),
                      ));
                    },
                    itemChanged: (SwipeItem item, int index) {
                      print("item: ${item.content.text}, index: $index");
                    },
                    leftSwipeAllowed: true,
                    rightSwipeAllowed: true,
                    upSwipeAllowed: true,
                    fillSpace: true,
                    likeTag: Container(
                      margin: const EdgeInsets.all(15.0),
                      padding: const EdgeInsets.all(3.0),
                      decoration: BoxDecoration(
                          border: Border.all(color: Colors.green)),
                      child: const Text('Like'),
                    ),
                    nopeTag: Container(
                      margin: const EdgeInsets.all(15.0),
                      padding: const EdgeInsets.all(3.0),
                      decoration:
                          BoxDecoration(border: Border.all(color: Colors.red)),
                      child: const Text('Nope'),
                    ),
                    superLikeTag: Container(
                      margin: const EdgeInsets.all(15.0),
                      padding: const EdgeInsets.all(3.0),
                      decoration: BoxDecoration(
                          border: Border.all(color: Colors.orange)),
                      child: const Text('Super Like'),
                    ),
                  ),

                  /// END SWIPE CARDS
                ),

                const SizedBox(height: 12),

                /// AD BUTTONS
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    /// UNDO BUTTON
                    GestureDetector(
                      onTap: () {
                        _matchEngine!.rewindMatch();
                      },
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
                      onTap: () {
                        _matchEngine!.currentItem?.like();
                      },
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

                    /// NOPE BUTTON
                    GestureDetector(
                      onTap: () {
                        _matchEngine!.currentItem?.nope();
                      },
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
