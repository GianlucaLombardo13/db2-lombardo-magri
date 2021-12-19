(function(){

    let upperBar, modalLogin, modalSignUp, landingPage, buyServicePage, confirmationPage;
    let pageOrchestrator=new PageOrchestrator();
    
    window.addEventListener('load',()=>{
        console.log("adding event listener");
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

    function LandingPage(landingPage,modalLogin,modalSignUp){
        this.landingPage=landingPage;
        this.login=modalLogin;
        this.signup=modalSignUp;

        this.reset=function(){
            this.landingPage.style.display="none";
            this.login.hide();
            this.signup.hide();
        }

        this.showLogin=function(){
            this.landingPage.style.display="block";
            this.login.show();
            this.signup.hide();
        }

        this.showSignUp=function (){
            this.landingPage.style.display="block";
            this.login.hide();
            this.signup.show();
        }

    }

    function ModalLogin(login,errorUsername,errorPassword){
        this.login=login;
        this.errorUsername=errorUsername;
        this.errorPassword=errorPassword;

        this.reset=function (){
            this.errorUsername.style.visibility="hidden";
            this.errorPassword.style.visibility="hidden";
        }

        this.show=function (){
            this.login.style.display="block";
            this.reset();
        }

        this.hide=function (){
            this.login.style.display="none";
            this.reset();
        }

        this.showErrorUsername=function(){
            this.errorUsername.style.visibility="visible";
            this.errorPassword.style.visibility="hidden";
        }

        this.showErrorPassword=function (){
            this.errorUsername.style.visibility="hidden";
            this.errorPassword.style.visibility="visible";
        }
    }

    function ModalSignUp(signUp,errorUsername,errorEmail,errorPassword){
        this.signUp=signUp;
        this.errorUsername=errorUsername;
        this.errorEmail=errorEmail;
        this.errorPassword=errorPassword;

        this.reset=function (){
            this.errorUsername.style.visibility="hidden";
            this.errorEmail.style.visibility="hidden";
            this.errorPassword.style.visibility="hidden";
        }

        this.show=function (){
            this.signUp.style.display="block";
            this.reset();
        }

        this.hide=function (){
            this.signUp.style.display="none";
            this.reset();
        }

        this.showErrorUsername=function(){
            this.errorUsername.style.visibility="visible";
            this.errorEmail.style.visibility="hidden";
            this.errorPassword.style.visibility="hidden";
        }

        this.showErrorEmail=function (){
            this.errorUsername.style.visibility="hidden";
            this.errorEmail.style.visibility="visible";
            this.errorPassword.style.visibility="hidden";
        }

        this.showErrorPassword=function (){
            this.errorUsername.style.visibility="hidden";
            this.errorEmail.style.visibility="hidden";
            this.errorPassword.style.visibility="visible";
        }
    }

    function BuyServicePage(){

    }

    function ConfirmationPage(){}

    function PageOrchestrator(){
        this.start=function (){
            console.log("start");
            upperBar=new UpperBar(document.getElementById("userDetails"),document.getElementById("loginButton"),document.getElementById("logoutButton"));

            modalLogin=new ModalLogin(document.getElementById("login"),document.getElementById("errorUsernameLogin"),document.getElementById("errorPasswordLogin"));
            modalSignUp=new ModalSignUp(document.getElementById("signup"),document.getElementById("errorUsernameSignUp"),document.getElementById("errorEmailSignUp"),document.getElementById("errorPasswordSignUp"))
            landingPage=new LandingPage(document.getElementById("landingPage"),modalLogin,modalSignUp);

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

    document.getElementById("loginAnchor").addEventListener('click',()=>{
        landingPage.showLogin();
    })

    document.getElementsByClassName("exit")[0].addEventListener('click',()=>{
        landingPage.reset();
    })

    document.getElementsByClassName("exit")[1].addEventListener('click',()=>{
        landingPage.reset();
    })

    window.onclick=function (e){
        if(e.target===document.getElementById("landingPage")){
            landingPage.reset();
        }
    }

    document.getElementById("signInButton").addEventListener('click',(e)=>{
        const form = e.target.closest("form");
        if(form.checkValidity()){
            makeCall("POST",'checkLogin',form,function (req){
                if(req.readyState===XMLHttpRequest.DONE){
                    const message = req.responseText;
                    if(req.status===200){ // SC_OK
                        const customer = JSON.parse(message);
                        sessionStorage.setItem('customer',JSON.stringify(customer));
                        upperBar.update(customer);
                    }else{
                        // Other Errors
                    }
                }
            });
        }else{
            form.reportValidity();
        }
    })

    document.getElementById("logoutButton").addEventListener('click',()=>{

    })

})();