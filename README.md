<H1>About:</H1>
As of Play Services 7.0, Google have made some great features available for us to implement into our apps - two of which include the Place Picker UI widget and Autocomplete component. These can both help to greatly improve existing solutions within your applications, or even help you to implement such features into your app in the future. Either way, providing a native and clean solution to such commonly used features really help to boost the user experience provided by your application.

<H2>Place Picker</H2>
The place picker widget is a UI component which provides an interactive map using the current location of a device. This map can be used to select a nearby place, which can be done in one of two ways:

<BR><B>Selecting the current location-</B> The user can move the displayed map pin to set their current location, if not satisfied with the pre-selected location.

<BR><B>Selecting a nearby place-</B> The user can select a place from the list of nearby places based on their current location, this list can be dragged open from the bottom of the interactive map.


<H2>Configurations:</H2>
<UL>
    <LI>Go to the Google API Console.</LI>
    <LI>Create or select a project.</LI>
    <LI>Click Continue to enable the Google Places API for Android.</LI>
    <LI>On the Credentials page, get an API key.</LI>
    <BR>Note: If you have an existing API key with Android restrictions, you may use that key.</B></LI>
    <LI>From the dialog displaying the API key, select Restrict key to set an Android restriction on the API key.</LI>
    <LI>In the Restrictions section, select Android apps, then enter your app's SHA-1 fingerprint and package name.</LI>
    <LI>Click Save.</LI>
</UL>
<BR>Your new Android-restricted API key appears in the list of API keys for your project.


<H2>Request an uplift in usage limits:</H2>

Usage of the Google Places API for Android is free and unlimited for all apps. However, to ensure fair use by all apps, there are tiered query limits on some methods.

The Google Places API for Android enforces a default limit of 1,000 requests per 24 hour period. If your app exceeds the limit, the app will start failing.

There is a further checkpoint when the app reaches 150,000 requests per 24 hour period. If your app exceeds the limit, the app will start failing again.

Please take steps to increase your limit early if you expect to exceed the default number of requests allowed. See the usage limits guide.
