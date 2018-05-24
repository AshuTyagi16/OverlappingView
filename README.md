# OverlappingImageView

Circular Image Views that overlap over one another.

![Scrollable Coins](https://github.com/AshuTyagi16/OverlappingView/blob/master/video2gif_20180524_231352%5B1%5D.gif)

To add in your .xml file :

<com.sasuke.overlappingimageview.OverlappingImageView
        android:id="@+id/oiv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:itemBorderColor="@color/colorPrimaryDark"
        app:itemBorderWidth="4dp"
        app:itemHeight="200dp"
        app:itemWidth="200dp"
        app:maxItemCount="20"
        app:offset="0.5" />

#Attributes

itemBorderColor -> Border color of circular image view
itemBorderWidth -> Border width of circular image view
itemHeight -> Height of circular image view
itemWidth -> Width of circular image view
maxItemCount -> Maximum number of items
offset -> Overlapping ratio

You can set it's weight between 0 to 1.

if (offset == 1) -> All the items will completely overlap over each other
if (offset == 0.5) -> All the items will overlap by 50% of the width (just like the above image)
if (offset == 0) -> All the items will arrange side by side

similarly you can play with the offset value according to your need.

#How to Load Images

You can use any of the following functions to load the image:

1. To load the images from url use:

setThumbnailUrl(ArrayList<String> imageUrlList, boolean removeItemOnSwipe) //If you want to enable item remove functionality on swipe :: set removeItemOnSwipe = true 

NOTEL -> ( THIS LIBRARY USE PICASSO INTERNALLY TO LOAD IMAGES SO DON'T FORGET TO ADD THE DEPENDENCY -> implementation 'com.squareup.picasso:picasso:2.71828' )

2. To load the images from drawable use:

setThumbnailDrawableRes(ArrayList<Integer> imageDrawableResList, boolean removeItemOnSwipe)

3.To laod the images from file use:

setThumbnailFile(ArrayList<File> imageFileList, boolean removeItemOnSwipe)

NOTE -> ( DON'T FORGET TO ADD READ_INTERNAL_STORAGE PERMISSION)

4. To load the images from Uri use:

setThumbnailUri(ArrayList<Uri> imageUriList, boolean removeItemOnSwipe)

ENJOY :)
