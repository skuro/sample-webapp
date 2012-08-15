<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:g="http://www.backbase.com/2008/gadget" xml:lang="en">
<head>
    <!-- Copyright © 2011 Backbase B.V. -->
    <!--<link rel="icon" type="image/png" href="favicon.png" />-->
    <meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
    <meta name="author" content="Backbase" />
    <meta name="keywords" content="widget" />
    <meta name="copyright" content="Copyright 2003-2012 Backbase b.v." />
    <meta name="description" content="Sample Widget created for example purposes" />

    <g:preferences>
        <g:preference name="location" label="My location" type="text" />
        <g:preference name="unit" label="Temperature scale" type="select-one" default="f">
            <g:enumeration value="c" label="Celsius" />
            <g:enumeration value="f" label="Fahrenheit" />
            <g:enumeration value="k" label="Kelvin" />
        </g:preference>
        <g:preference name="switch" label="Switch" type="checkbox" default="false"/>
    </g:preferences>

    <style type="text/css">
        .ex-sampleWidget {
            padding: 5px;
        }
    </style>

    <script type="text/javascript">

        if(!window.examples) window.examples = {};
        if(!window.examples.widgets) window.examples.widgets = {};

        new function (){
            var SampleNewClient = {
                onLoad: function(oWidget) {
                    var textArea = $(oWidget.body).find(".ex-WidgetTextArea");
                    if(oWidget.getPreference != null){
                        textArea.val(String(oWidget.getPreference("Data")));
                    }
                    else {
                        textArea.val("Unable get to preference data");
                    }

                    var ContextInfoArea = $(oWidget.body).find(".ex-ContextInfo");
                    ContextInfoArea.html("Current Page name: " + b$.portal.getCurrentPage().name + " - Current Portal name: " + b$.portal.getCurrentPortal().name + "<br/>");

                },
                onMaximize: function(oWidget) {
                    oWidget.refreshIncludes();

                    var textArea = $(oWidget.body).find(".ex-WidgetTextArea");
                    if(oWidget.getPreference != null){
                        textArea.val(String(oWidget.getPreference("Data")) + "maximized");
                    }
                    else {
                        textArea.val("Unable get to preference data");
                    }
                },
                onRestore: function(oWidget) {
                    var textArea = $(oWidget.body).find(".ex-WidgetTextArea");
                    if(oWidget.getPreference != null){
                        textArea.val(String(oWidget.getPreference("Data")) + "restored");
                    }
                    else {
                        textArea.val("Unable get to preference data");
                    }
                },
                saveValue: function(oWidget, textArea) {
                    if(oWidget.setPreference != null){
                        oWidget.setPreference("Data", textArea.value);
                    }
                    //onchange="examples.widgets.SampleNewClient.saveValue(__WIDGET__, this)"
                }
            }

            // Register
            window.examples.widgets.SampleNewClient = SampleNewClient;
        }
    </script>
    <script type="text/javascript" data-bb-mode="Maximized">
        //console.log('perspective-specific script loaded');
    </script>
</head>
<body class="w_inquiry_status"
      g:onload="examples.widgets.SampleNewClient.onLoad(__WIDGET__)"
      g:onmaximize="examples.widgets.SampleNewClient.onMaximize(__WIDGET__)"
      g:onrestore="examples.widgets.SampleNewClient.onRestore(__WIDGET__)"
      g:onPrefModified="examples.widgets.SampleNewClient.onLoad(__WIDGET__)">
<div class="ex-sampleWidget">
    <h1>I'm back</h1>
    <b>Public API - Context Info:</b> <br/>
    <div class="ex-ContextInfo"></div>
    <br/>
    <b>'Data' preference value:</b> <br/>
    <textarea class="ex-WidgetTextArea" rows="5" style="width:100%;"></textarea>
</div>
</body>
</html>
