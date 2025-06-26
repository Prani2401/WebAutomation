const { assert } = require("nightwatch");

module.exports={
    before(browser){
        browser.url("https://testautomationpractice.blogspot.com/");
    },
    after(browser){
        browser.pause(3000);
        browser.end()
    },
    "hi":function(browser){
        
        browser.url(function(result){
            console.log(result.value);
        });

        browser.setValue("#email","kichupannu@gmail.com");
        // browser.pause()
        browser.getText("#Wikipedia1 > h2",function(result){
            assert.equal("Tabs",result.value);
        });
        browser.useXpath();
        browser.click("//input[@value='female']");
        browser.useCss();
        browser.setValue("#country", "United Kingdom");
        
        browser.assert.visible("#datepicker");
        browser.pause(3000)
            .useXpath()
        browser.perform(function(){
            browser.actions()
            browser.doubleClick("//*[@id='HTML10']/div[1]/button")
            browser.perform()
        })
    },
};