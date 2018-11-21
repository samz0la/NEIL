# NEIL
1. In my conversations (pre bootcamp) with representatives of DeepDive, I framed my questions and decision to choose the 
Java + Android bootcamp based on the information of past alumni choosing games for their personal and final projects. Even though
I'm not the biggest gamer, I enjoy seeing visual representation of my work displayed somewhere. I also believe that playing a 
mindless game occasionally is a great way to take your mind off other stressful tasks that daily life presents itself.

2. As of now I believe my app is in a somewhat usable state. My collision detection is a little off and my explosion animation 
is off as well. When you pass a certain distance (I believe around 800) in the game the enemy only displays the first
enemy. I currently have a red line under my draw method in my Game Panel because
I'm not calling the super.class. The problem when I uncomment it out of my code is that the surface View 
shows the separation of the background image as it scrolls acrossed the screen.

3. I have currently tested it on 2 emulators. A Nexus 5x with API 28 running on it, and a Pixel 2 XL running API 22. Both run
the game fine but the Pixel has somewhat of a lag on the login screen. The login screen is locked to portrait orientation and the
game is locked to landscape orientation.

4. I have several third party libraries. Hitomis: Circle menu, retrofit, room and facebook: stetho.

5. I have google login and facebook login.

6. Other than fixing the collision, in game, to looking more realistic, I am happy with the aesthetic/cosmetics of my app.

7. Stretch Goals: 
    * fix collision detection to check against RGB instead of the alpha channel
    * fix explosion animation
    * adding music
    * import it to ios 
    * Put it up on the appstore 
    
8. * [WireFrame](docs/NEIL_wireframe.pdf)
   * [User Stories](docs/UserStories.pdf)

9. * [ERD](docs/NEIL_Erd.pdf)
   * [DDL](docs/ddl.sql)
   
10. [Javadocs](docs/javadocs)


11. Copyrights and Licenses
    * [NEIL License](LICENSE.md)
    * [Hitomis Circle Menu](https://github.com/Hitomis/CircleMenu#licence)
    * [Google](licenses/google_license.md)
    * [Stetho](licenses/stetho_license.md)

12. Build Instructions
    * Go to the Neil [Repo](https://github.com/samz0la/NEIL)
    * Go to the Clone or download button and clone to clipboard.
    * Next Open up IntelliJ and hit Import project from version control.
    * Paste the cloned repository.
    * 
