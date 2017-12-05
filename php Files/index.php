<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Roleplay Database</title>
<link href="multiColumnTemplate.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
  <header>
    <div class="primary_header">
      <h1 class="title"><img src="https://cdn.discordapp.com/attachments/358117230690435073/387482053739937823/rpgportallogo.png"></h1>
    </div>
    <nav class="secondary_header" id="menu">
      <ul>
        <li><a class="scroller" style="text-decoration:none;" href="#about">ABOUT</a></li>
        <li><a class="scroller" style="text-decoration:none;" href="#install">DEMO</a></li>
        <li><a class="scroller" style="text-decoration:none;" href="#theteam">MEET THE TEAM</a></li>
      </ul>
    </nav>
  </header>
  <div class="content">
  <section id="log">
    <h2 class="noDisplay">Main Content</h2>

  </section>
  <div class="info" id="about">
      <h2><div align="center">About Our Project</div></h2>
      <p>I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.I'll be filling this in soon with tons of information about the project and such when I get the chance.

      </p>
      <div align="right"><a class="scroller" href="#menu">To Menu</a></div>
  </div>

   <div class="info" id="install">
      <h2><div align="center">Demo</div></h2>

      <div align="center"><iframe src="https://drive.google.com/file/d/1JHYBKZuUYmWoJgNDEKk-z_s46Bo3M1CG/preview" width="640" height="480"></iframe></div>

      <div align="right"><a class="scroller" href="#menu">To Menu</a></div>
  </div>

  <div class="row" id="theteam">

    <div class="columns">
        <div class="info">
      <p class="thumbnail_align"> <img src="https://cdn.discordapp.com/attachments/358117230690435073/387490454243115009/maxresdefault_1.jpg" alt="" class="thumbnail"/> </p>
      <h4>Luis Pamintuan</h4>
      <p>
      -Placeholder<br /><br />

          Infoblurb here. This is where we can put a little about ourselves.</p>
          <div align="right"><a class="scroller" href="#menu"><font color="black">To Menu</font></a></div></div>
    </div>
    <div class="columns">
        <div class="info">
      <p class="thumbnail_align"> <img src="http://i0.kym-cdn.com/entries/icons/original/000/000/091/TrollFace.jpg" alt="" class="thumbnail"/> </p>
      <h4>Justin Armijo</h4>
      <p>
      -Placeholder<br /><br />
          aboutme section here </p>
          <div align="right"><a class="scroller" href="#menu"><font color="black">To Menu</font></a></div></div>
    </div>
    <div class="columns">
        <div class="info">
      <p class="thumbnail_align"> <img src="https://cdn.discordapp.com/attachments/358117230690435073/387490732354830347/9k.png" alt="" class="thumbnail"/> </p>
      <h4>Jack Bui</h4>
      <p>
      -Placeholder<br /><br />

      about me section goes here<br /><br /><br /><br /></p>
      <div align="right"><a class="scroller" href="#menu"><font color="black">To Menu</font></a></div></div>

    </div>

  </div>


  <footer class="secondary_header footer">
    <div class="copyright">&copy;2017 - <strong>RPG Portal</strong><br /><br />Wiki by Justin Armijo</div>
  </footer>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
var navbar = document.getElementById("menu");
var sticky = navbar.offsetTop;

function stickyBar() {
  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky")
  } else {
    navbar.classList.remove("sticky");
  }
}

$(function() {
  $('.scroller').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top
        }, 800); // The number here represents the speed of the scroll in milliseconds
        return false;
      }
    }
  });
});
</script>
</body>
</html>
