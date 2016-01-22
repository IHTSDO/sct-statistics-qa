unzip -o stats-viewer.zip -d site
del site/stats-viewer/data/*.*
java -Xms3G -Xmx7G -jar stats-gen.jar config/extConfig.xml -clean
copy stats_output/*.* site/stats-viewer/data	
copy patterns_output/*.json site/stats-viewer/data	
copy process_info/*.json site/stats-viewer/data	
java -Xms2G -Xmx2G -jar nanohttpd-webserver-${nano_webserver_version}-jar-with-dependencies.jar -d ext_site/stats-viewer
