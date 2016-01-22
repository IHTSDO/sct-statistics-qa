unzip -o stats-viewer.zip -d site
rm site/stats-viewer/data/*
java \
	-Xms3G \
	-Xmx7G \
	-jar stats-gen.jar config/extConfig.xml -clean
cp stats_output/*.* site/stats-viewer/data	
cp patterns_output/*.json site/stats-viewer/data	
cp process_info/*.json site/stats-viewer/data	
java -Xms2G -Xmx2G -jar nanohttpd-webserver-${nano_webserver_version}-jar-with-dependencies.jar -d site/stats-viewer
