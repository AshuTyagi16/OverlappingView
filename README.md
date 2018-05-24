# OverlappingImageView

Circular Image Views that overlap over one another. <br />

![Scrollable Coins](https://github.com/AshuTyagi16/OverlappingView/blob/master/video2gif_20180524_231352%5B1%5D.gif)
<br />
<br />
<b>To add in your .xml file : </b>

<com.sasuke.overlappingimageview.OverlappingImageView <br />
        android:id="@+id/oiv" <br /> 
        android:layout_width="wrap_content" <br />
        android:layout_height="wrap_content" <br />
        app:itemBorderColor="@color/colorPrimaryDark" <br />
        app:itemBorderWidth="4dp" <br />
        app:itemHeight="200dp" <br />
        app:itemWidth="200dp" <br />
        app:maxItemCount="20" <br />
        app:offset="0.5" /> <br />

<b>Attributes</b> <br />

itemBorderColor -> Border color of circular image view <br />
itemBorderWidth -> Border width of circular image view <br />
itemHeight -> Height of circular image view <br />
itemWidth -> Width of circular image view <br />
maxItemCount -> Maximum number of items <br />
offset -> Overlapping ratio <br />

You can set it's weight between 0 to 1. <br />

if (offset == 1) -> All the items will completely overlap over each other <br />
if (offset == 0.5) -> All the items will overlap by 50% of the width (just like the above image) <br />
if (offset == 0) -> All the items will arrange side by side <br />

similarly you can play with the offset value according to your need. <br />

<b>How to Load Images</b> <br />

You can use any of the following functions to load the image: <br /> 

1. To load the images from url use: <br />

setThumbnailUrl(ArrayList<String> imageUrlList, boolean removeItemOnSwipe) //If you want to enable item remove functionality on swipe :: set removeItemOnSwipe = true <br />

NOTEL -> ( THIS LIBRARY USE PICASSO INTERNALLY TO LOAD IMAGES SO DON'T FORGET TO ADD THE DEPENDENCY -> implementation 'com.squareup.picasso:picasso:2.71828' ) <br />

2. To load the images from drawable use:<br />

setThumbnailDrawableRes(ArrayList<Integer> imageDrawableResList, boolean removeItemOnSwipe) <br /> 

3.To laod the images from file use: <br />

setThumbnailFile(ArrayList<File> imageFileList, boolean removeItemOnSwipe) <br /> 

NOTE -> ( DON'T FORGET TO ADD READ_INTERNAL_STORAGE PERMISSION) <br />

4. To load the images from Uri use: <br />

setThumbnailUri(ArrayList<Uri> imageUriList, boolean removeItemOnSwipe) <br /><br />

ENJOY :)
