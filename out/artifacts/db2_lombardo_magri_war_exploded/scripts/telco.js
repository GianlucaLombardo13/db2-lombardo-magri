(function(){

    let upperBar, landingPage, buyServicePage, confirmationPage;
    let pageOrchestrator=new PageOrchestrator();
    
    window.addEventListener('load',()=>{
        pageOrchestrator.start();
        pageOrchestrator.refresh();
    },false)
    
    function UpperBar(userDetails,loginButton,logoutButton){
        this.userDetails=userDetails;
        this.loginButton=loginButton;
        this.logoutButton=logoutButton;
        
        this.reset=function (){
            this.userDetails.style.display="none";
            this.loginButton.style.display="block";
            this.logoutButton.style.display="none";
        }

        this.update=function(user){
            let username;
            this.userDetails.innerHTML="";
            username=document.createElement("h3");
            username.textContent=user.username;
            this.userDetails.appendChild(username);
            this.userDetails.style.display="block";
            this.logoutButton.style.display="block";
            this.loginButton.style.display="none";
        }
    }

    function LandingPage(landingPage,login,signup){
        this.landingPage=landingPage;
        this.login=login;
        this.signup=signup;

        this.reset=function(){
            this.landingPage.style.display="none";
            this.login.style.display="none";
            this.signup.style.display="none";
        }

        this.showLogin=function(){
            this.landingPage.style.display="block";
            this.login.style.display="block";
            this.signup.style.display="none";
        }

        this.showSignUp=function (){
            this.landingPage.style.display="block";
            this.login.style.display="none";
            this.signup.style.display="block";
        }

    }

    function BuyServicePage(){

    }

    function ConfirmationPage(){}

    function PageOrchestrator(){
        this.start=function (){
            upperBar=new UpperBar(document.getElementById("userDetails"),document.getElementById("loginButton"),document.getElementById("logoutButton"));
            landingPage=new LandingPage(document.getElementById("landingPage"),document.getElementById("login"),document.getElementById("logout"));
            buyServicePage=new BuyServicePage();
            confirmationPage=new ConfirmationPage();
        }

        this.refresh=function (){
            let user=JSON.parse(sessionStorage.getItem("user"));
            if(user==null){
                upperBar.reset();
            }else{
                upperBar.update(user);
            }

            landingPage.reset();
        }
    }


    document.getElementById("loginButton").addEventListener('click',()=>{
        landingPage.showLogin();
    })
    document.getElementById("signUpAnchor").addEventListener('click',()=>{
        landingPage.showSignUp();
    })

    document.getElementById("logoutButton").addEventListener('click',()=>{

    })
})();