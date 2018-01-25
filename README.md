[![](https://jitpack.io/v/barisatalay/filter-dialog-activity.svg)](https://jitpack.io/#barisatalay/filter-dialog-activity)

# filter-dialog-activity
Filter Dialog Activity for Android Projects


## Screen Shots

![alt tag](screen/Capture.PNG)

## Usage

### Step 1
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2

Add dependencies in build.gradle.
```groovy
    dependencies {
       compile 'com.github.barisatalay:filter-dialog-activity:1.0.6'
    }
```

### Step 3 (Use of library)
Create FilterDialog for Custom class;
```java
   List<mdlPerson> personList = new ArrayList<>();

   personList.add(new mdlPerson("1","Bir"));
   personList.add(new mdlPerson("2","İki"));
   personList.add(new mdlPerson("3","Üç"));
   personList.add(new mdlPerson("4","Dört"));
   
   final FilterDialog filterDialog = new FilterDialog(MainActivity.this);
   filterDialog.setToolbarTitle("Model Filter");
   filterDialog.setSearchBoxHint("You can search");
   filterDialog.setList(personList);
   
   /*
   * nameField : model's is the part that will appear on the screen.
   * idField : id section in the model.
   * dialogListener : when any row item selected, selected item will be return from interface
   */
   filterDialog.show("code", "name", new DialogListener() {
   	@Override
   	public void onResult(FilterItem selectedItem) {
   		Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
		filterDialog.dispose();
   	}
   });
``` 

### Step 3 (Use of library)
Create FilterDialog for String class;
```java
   List<String> stringList = new ArrayList<>();
   stringList.add("Item 1");
   stringList.add("Item 2");
   stringList.add("Item 3");
   stringList.add("Item 4");
   stringList.add("Item 5");
   stringList.add("Item 6");
   stringList.add("Item 7");
   
   final FilterDialog filterDialog = new FilterDialog(MainActivity.this);
   filterDialog.setToolbarTitle("String Filter");
   filterDialog.setSearchBoxHint("You can search");
   filterDialog.setList(stringList);
   
   filterDialog.backPressedEnabled(false);
   filterDialog.setOnCloseListener(new View.OnClickListener() {
   	@Override
        public void onClick(View view) {
        	filterDialog.dispose();
        }
   });
   
   /*
   * When you have List<String,Integer,Boolean,Double,Float> should be use this method
   */
   filterDialog.show(new DialogListener() {
   	@Override
   	public void onResult(FilterItem selectedItem) {
   		Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
		filterDialog.dispose();
   	}
   });
``` 

## Step 4 - Customizing Filter Dialog Activity is very simple
Just define your own project

Color customization
```xml
<color name="filterdialog_background">#eae7e7</color>
<color name="filterdialog_toolbar">#163645</color>
<color name="filterdialog_toolbar_text">@color/white</color>
<color name="filterdialog_searchbox_border">@color/white</color>
<color name="filterdialog_searchbox_background">#DDD4D4</color>
<color name="filterdialog_searchbox_text">@color/colorTextStandart</color>
<color name="filterdialog_searchbox_hint">#747373</color>
<color name="filterdialog_searchbox_icon">#0e242e</color>
<color name="filterdialog_row_text">@color/colorTextStandart</color>
```

Text customization
```xml
<string name="filterdialog_back">Back</string>
<string name="filterdialog_clear">Clear</string>
```

## Thanks
- [Emre Can Akcan](https://github.com/emreakcan)


## Contact me
 If you have a better idea or way on this project, please let me know, thanks :)

[Email](mailto:b.atalay07@hotmail.com)

[My Blog](http://brsatalay.blogspot.com.tr)

[My Linkedin](http://linkedin.com/in/barisatalay07/)
