package com.example.weather3;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false, inAddr = false, inChargeTp = false, inCity = false;

        String addr = null, chargeTp = null, city = null;
        String lat = null, longi = null, statUpdateDatetime = null;


        try{
            URL url = new URL("http://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("tmEf")){ //title 만나면 내용을 받을수 있게 하자
                            inAddr = true;
                        }
                        if(parser.getName().equals("city")){ //title 만나면 내용을 받을수 있게 하자
                            inCity = true;
                        }
                        if(parser.getName().equals("wf")){ //address 만나면 내용을 받을수 있게 하자
                            inChargeTp = true;
                        }

                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inAddr){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr = parser.getText();
                            inAddr = false;
                        }
                        if(inChargeTp){ //isAddress이 true일 때 태그의 내용을 저장.
                            chargeTp = parser.getText();
                            inChargeTp = false;
                        }
                        if(inCity){ //isAddress이 true일 때 태그의 내용을 저장.
                            city = parser.getText();
                            inCity = false;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("data")){
                            status1.setText( status1.getText()+"도시:"+city+"\n 날짜 : "+addr +"\n 날씨: "+ chargeTp  +"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
        }
    }
}