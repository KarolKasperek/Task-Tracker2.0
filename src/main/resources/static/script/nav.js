document.getElementById("navBar").innerHTML = `
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Tilt+Warp&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Gidugu&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        #menu {
            display: flex;
            justify-content: center;
            list-style-type: none;
            background-color: #1D2125;
            height: 65px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, .05), 0 8px 16px rgba(0, 0, 0, .05);
        }

        .menuItem {
            margin-top: -4px;
            margin-left: 15px;
            margin-right: 15px;
            float: left;
            font-family: "Noto Sans Ethiopic", sans-serif;
            font-optical-sizing: auto;
            font-weight: 500;
            font-style: normal;
            font-variation-settings: "wdth" 100;
            transform: translateY(10px);
        }

        .navItem {
            display: inline-block;
            padding: 14px;
            border-radius: 10px;
            text-decoration: none;
            font-size: 18px;
            color: #8C9BAB;
            user-select: none;
            height: 54px;
        }

        .navItem:hover {
            background-color: rgba(191, 219, 248, 0.15);
            transition: 0.1s;
        }

        .break {
            width: 5vw;
            max-width: 70px;
        }

        #logo {
            height: 16px;
            font-family: "Tilt Warp", sans-serif;
            font-size: 40px;
            font-weight: 400;
            font-style: normal;
            color: #8C9BAB;
            user-select: none;
            transform: translateY(-15px);
        }

        #logo:hover {
            background-color: #1D2125;
        }

        #messages {
            margin-right: -20px;
        }

        #profile {
            margin-left: -20px;
        }

        #signOutStick {
            transform: translateY(24px);
            font-weight: bold;
            font-family: Helvetica, Arial, sans-serif;
            color: rgb(80, 80, 80);
            user-select: none;
        }
    </style>

    <ul id="menu">
        <li class="menuItem"><h1 id="logo" class="navItem">TaskTracker</h1></li>
        <li class="menuItem"><div class="break"></div></li>
        <li class="menuItem"><a href="/" class="navItem" id="projects">Projects</a><hr id="projectsHr"></li>
        <li class="menuItem"><a href="/members" class="navItem" id="members">Members</a><hr id="membersHr"></li>
        <li class="menuItem"><a href="/share" class="navItem" id="share">Share</a><hr id="shareHr"></li>
        <li class="menuItem"><div class="break"></div></li>
        <li class="menuItem" id="signOutStick">|</li>
        <li class="menuItem"><a href="/sign-out" id="profile" class="navItem">Sign out</a></li>
    </ul>
`;