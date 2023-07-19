# Some attributes that you'll need to keep (to be honest I'm not sure which ones really need to be kept here, but this is what works for me)
-keepattributes !LocalVariableTable,!LocalVariableTypeTable,Exceptions,InnerClasses,Signature,Deprecated,LineNumberTable,*Annotation*,EnclosingMethod,SourceFile,LineNumberTable,Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,EnclosingMethod


-keep class !pl.freehc.fhcuboids.** { *; }
-keep public class pl.freehc.fhcuboids.App {
    public void onEnable();
	public void onDisable();
}

# Keep event handlers
-keep,allowobfuscation class * extends org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler <methods>;
}

-dontoptimize
-dontshrink


-dontwarn
-dontnote
-assumenosideeffects class !pl.freehc.fhcuboids.** { *; }

-libraryjars <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.compiler.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.datatransfer.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.desktop.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.instrument.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.logging.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.management.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.management.rmi.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.naming.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.net.http.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.prefs.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.rmi.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.scripting.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.se.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.security.jgss.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.security.sasl.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.smartcardio.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.sql.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.sql.rowset.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.transaction.xa.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.xml.crypto.jmod(!**.jar;!module-info.class)
-libraryjars <java.home>/jmods/java.xml.jmod(!**.jar;!module-info.class)