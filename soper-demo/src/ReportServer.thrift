namespace java com.daqsoft.schedule.face
service ReportServer{
	oneway void reportJobDone(1:string report);
}