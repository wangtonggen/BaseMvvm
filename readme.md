# 描述
该库使用的mvvm+rxjava2+retrofit2+okhttp3+glide架构模式 可以很方便的使用

# 使用
1.lib里面有使用到butterknife 首先在 project build.gradle添加  
dependencies {  
classpath 'com.android.tools.build:gradle:3.6.3'  
classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'  
}  
和  
allprojects {  
repositories {  
google()  
jcenter()  
maven { url 'https://jitpack.io' }

    }
}

2.在 app的build.gradle头部添加  
apply plugin: 'com.jakewharton.butterknife'

3.在APP 里面的 build.gradle添加依赖  
implementation'com.github.wangtonggen:BaseMvvm:1.0'  
还需要在APP 里面的 build.gradle添加 apply plugin:
'com.jakewharton.butterknife'  
//butterknife  
implementation'com.jakewharton:butterknife:10.2.1'  
annotationProcessor'com.jakewharton:butterknife-compiler:10.2.1'


