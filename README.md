# Childrens-Card-Game-Bot

Download the JAR here https://sabercathost.com/4L0s/yugi.jar

BUGS/ISSUES

-The bot won't stop until the current cycle is completed. You must alt-F4 or otherwise kill the application to free your cursor.

-Long loading times may be an issue since the bot's timings are mostly hardcoded. Not encountered but possible. May need to allow custom timings.

Do you play the greatest children's card game ever? Do you play it on Yugioh! Legacy of the Duelist? 

If you do, you have probably noticed how much time, how many packs, how many duels, to get 
those couple cards you need to finish your netdeck. Well here is your savior(if you want cards slightly more legitimately than a saved game file).

My Childrens-Card-Game-Bot will autonomously earn you DP(heh) and buy packs while you go play a real game(ping pong perhaps)!

For this application to work as intended, you will need to spend a few minutes inputting the correct screen coordinates and RGB values
for your specific gaming environment. Fortunately, there is a built-in screen coordinate and RGB value tool on the top right of the window.

Simply toggle on the Coord Tool, and fill in the corresponding key coordinates below:

Deck X/Y- Location of your deck in a duel.

Yes X/Y - Location of the 'Yes' button when surrendering in a duel.

Flag X/Y - Location of the little white surrender flag that bounces above your deck. Pick coordinates where the RGB values do not fluctuate much.

Border X/Y - When you buy a pack and the cards are done unveiling, the bottom right card becomes surrounded by a yellow border. 
              This is what is used to determine that the bot can move onto the next pack.
              
FlagRGB
BorderRGB

The bot will look at the color of the pixel at the given coordinates and decide to proceed or not. It decides by taking the RGB values of the 
given pixel and comparing it to a minimum threshold for each value. You set this minimum threshold. The closer it is without going over is recommended.

Auto-Surrender
1. Make sure you are at the Duelist Challenge screen where portraits are shown. 
2. Check the Surrender radio button at the top right of the bot window.
3. MAKE SURE YOU UPDATED YOUR COORDINATES/RGB.
4. Click on the 'Run' button. You have 3 seconds to click back to the game and leave your cursor on the desired portrait.
5. Walk away because you can't do anything else on your computer.

Auto-Buy
1. Make sure you are at the Buy Packs screen with the portraits shown.
2. Check the Buy Packs radio button at the top right of the bot window.
3. MAKE SURE YOU UPDATED YOUR COORDINATES/RGB.
4. Click on the 'Run' button. You have 3 seconds to click back to the game and leave your cursor on the desired portrait.
5. Walk away because you can't do anything else on your computer.
