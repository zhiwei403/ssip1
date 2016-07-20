

var express = require('express'),
    app = express(),
    
    Account = require("./models/account"),
    mongoose = require('mongoose'),
    passport = require("passport"),
    basicAuth = require('basic-auth'),
    bodyParser = require("body-parser"),
   
    LocalStrategy = require("passport-local"),
    passportLocalMongoose = require("passport-local-mongoose");  //libraries


mongoose.connect("mongodb://localhost/test");  //connect to mongodb database test
app.set('view engine', 'ejs'); //using engine of ejs file
app.use(bodyParser.urlencoded({extended: true}));
app.use(require("express-session")({
    secret: "kiss my ass",
    resave: false,
    saveUninitialized: false
}));

app.use(passport.initialize());
app.use(passport.session());

passport.use(new LocalStrategy(Account.authenticate()));
passport.serializeUser(Account.serializeUser());
passport.deserializeUser(Account.deserializeUser());


// AUTH Routes
//error login form
app.get("/error", function(req, res) {
	   res.render("error"); 
	});


//create account success
app.get('/success', function (req, res) {
	  res.render('success');
	});

// deleteUser form
app.get("/delete", function(req, res, next) {
	   res.render("deleteuser"); 
	});



app.get("/viewall", function(req, res, next) {
	   res.render("viewall"); 
	});



//view all User form
app.get('/view', function (req, res) {
	
console.log('getting all user');
Account.find({})
.exec(function(err, results) {
	if(err) {
		res.send('error has occured');
	}else{
		
		console.log(results);
		res.json(results);
	}
});
});

app.get('/viewall/:id', function (req, res) {
	
	console.log('getting one user');
	Account.findOne({
		_id:req.params.id
	})
	.exec(function(err, account) {
	if(err) {
		res.send('error occured');
	}else{
		console.log(account);
		res.json(account);
	}
	})
})

	app.delete('/viewall/:id',function(req,res){
		  // rest of code here
		  // assume your model name Account
		  Account.remove({ _id: req.params.id },function(err,doc) {
		      if(err) {
		         return res.status(400).send({msg: 'Error occurred during delete account'});
		      }
		      return res.status(200).send({msg: 'Successfully deleted'});
		  });
		});





// LOGIN for user
// render login form
app.get("/", function(req, res) {
   res.render("login"); 
});

//login for user
//middleware
app.post("/login", passport.authenticate("local", {
   successRedirect: "http://localhost:8082/viewImage.html",
   failureRedirect: "http://localhost:8081/error" 
}), function(req, res) {
    
});

//logout from basicauth
app.get('/logout', function (req, res) {
    res.set('WWW-Authenticate', 'Basic realm=Authenticate Required');
    return res.sendStatus(401);
   // res.send("<a href='/login'>Show Users</a>");
});

//basicauth for admin login

var auth = function (req, res, next) {
	  function unauthorized(res) {
	    res.set('WWW-Authenticate', 'Basic realm=Authenticate Required');
	    return res.send(401);
	  };

	  var user = basicAuth(req);

	  if (!user || !user.name || !user.pass) {
	    return unauthorized(res);
	  };

	  if (user.name === 'admin' && user.pass === 'a111') {
	    return next();
	  } else {
	    return unauthorized(res);
	  };
	};
//LOGIN for admin
//render login form
app.get("/register", auth, function(req, res) {
   res.render("register"); 
   
   
});

// register post
app.post("/register", function(req,res){
    Account.register(new Account({username: req.body.username}), req.body.password, function(err, user){
        if(err){
            console.log(err);
           
            return res.render('/register');
        }
        passport.authenticate("local")(req, res, function(){
           res.redirect("/success"); 
        });
    });
});

app.listen(8081, function () {
  console.log('ImageViewer listening on port 8081!');
});