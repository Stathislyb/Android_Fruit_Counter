package uowm.st0186.finalexams;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InputsActivity extends ActionBarActivity {
	String name,aem;
	int score_1,score_2,score_3;
	Button butt_fruit_1,butt_fruit_2,butt_fruit_3,butt_exit,butt_reset,butt_submit,butt_fruit_1_min,butt_fruit_2_min,butt_fruit_3_min;
	Spinner spinner1,spinner2,spinner3;
	TextView text1,text2,text3;
	MediaPlayer sound_neg_error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay_layout);
		Intent Incoming_intent = getIntent();
    	Bundle extras = Incoming_intent.getExtras();
    	name =(String) extras.get("name");
    	aem =(String) extras.get("aem");
    	
    	score_1=score_2=score_3=0;
    	sound_neg_error = MediaPlayer.create(this, R.raw.sound_neg_error);
    	
    	text1 = (TextView) findViewById(R.id.textView1);
    	butt_fruit_1 = (Button) findViewById(R.id.button1_plus);
    	butt_fruit_1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				score_1+=1;
				text1.setText(String.valueOf(score_1));
			}
		});
    	butt_fruit_1_min = (Button) findViewById(R.id.Button1_min);
    	butt_fruit_1_min.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(score_1 >0){
					score_1-=1;
					text1.setText(String.valueOf(score_1));
				}else{
					sound_neg_error.start();
				}
			}
		});
    	
    	text2 = (TextView) findViewById(R.id.textView2);
    	butt_fruit_2 = (Button) findViewById(R.id.button2_plus);
    	butt_fruit_2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				score_2+=1;
				text2.setText(String.valueOf(score_2));
			}
		});
    	butt_fruit_2_min = (Button) findViewById(R.id.Button2_min);
    	butt_fruit_2_min.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(score_2 >0){
					score_2-=1;
					text2.setText(String.valueOf(score_2));
				}else{
					sound_neg_error.start();
				}
			}
		});
    	
    	text3 = (TextView) findViewById(R.id.textView3);
    	butt_fruit_3 = (Button) findViewById(R.id.button3_plus);
    	butt_fruit_3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				score_3+=1;
				text3.setText(String.valueOf(score_3));
			}
		});
    	butt_fruit_3_min = (Button) findViewById(R.id.Button3_min);
    	butt_fruit_3_min.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(score_3 >0){
					score_3-=1;
					text3.setText(String.valueOf(score_3));
				}else{
					sound_neg_error.start();
				}
			}
		});
    	
    	
    	butt_exit = (Button) findViewById(R.id.exit);
    	butt_exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				InputsActivity.this.finish();
			}
		});
    	
    	butt_reset = (Button) findViewById(R.id.restart);
    	butt_reset.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				score_1 = 0;
				score_2 = 0;
				score_3 = 0;
				text1.setText(String.valueOf(score_1));
				text2.setText(String.valueOf(score_2));
				text3.setText(String.valueOf(score_3));
			}
		});
    	
    	butt_submit = (Button) findViewById(R.id.submit);
    	butt_submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				params.put("name",name);
				params.put("aem",aem);
				params.put("score_1",String.valueOf(score_1));
				params.put("score_2",String.valueOf(score_2));
				params.put("score_3",String.valueOf(score_3));
				
				client.post("http://www.vaterlo.gr/uowm/addScores.php", params,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(String response){
						Toast.makeText(getApplicationContext(), "Scores successfully sent to server.",
								   Toast.LENGTH_LONG).show();
						Intent intent_results = new Intent(InputsActivity.this, MainActivity.class);
						startActivity(intent_results);
						InputsActivity.this.finish();
					}
					@Override
					public void onFailure(int statusCode, Throwable error,String content){
						Toast.makeText(getApplicationContext(), "There was a problem connecting to server.",
								   Toast.LENGTH_LONG).show();
					}
				});
			}
		});
    	
    	spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener( new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
				if(pos==0){
					butt_fruit_1.setBackgroundResource(R.drawable.apple_button);
				}else if(pos==1){
					butt_fruit_1.setBackgroundResource(R.drawable.banana_button);						
				}else if(pos==2){
					butt_fruit_1.setBackgroundResource(R.drawable.strawberry_button);						
				}else if(pos==3){
					butt_fruit_1.setBackgroundResource(R.drawable.cherry_button);								
				}else if(pos==4){
					butt_fruit_1.setBackgroundResource(R.drawable.pear_button);								
				}else{
					butt_fruit_1.setBackgroundResource(R.drawable.other_button);							
				}
			}
			@Override
		  	public void onNothingSelected(AdapterView<?> arg0) {
				butt_fruit_1.setBackgroundResource(R.drawable.apple_button);
			}	
		});
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener( new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
				if(pos==0){
					butt_fruit_2.setBackgroundResource(R.drawable.apple_button);
				}else if(pos==1){
					butt_fruit_2.setBackgroundResource(R.drawable.banana_button);						
				}else if(pos==2){
					butt_fruit_2.setBackgroundResource(R.drawable.strawberry_button);						
				}else if(pos==3){
					butt_fruit_2.setBackgroundResource(R.drawable.cherry_button);								
				}else if(pos==4){
					butt_fruit_2.setBackgroundResource(R.drawable.pear_button);								
				}else{
					butt_fruit_2.setBackgroundResource(R.drawable.other_button);							
				}
			}
			@Override
		  	public void onNothingSelected(AdapterView<?> arg0) {
				butt_fruit_2.setBackgroundResource(R.drawable.banana_button);
			}	
		});
		
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner3.setOnItemSelectedListener( new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
				if(pos==0){
					butt_fruit_3.setBackgroundResource(R.drawable.apple_button);
				}else if(pos==1){
					butt_fruit_3.setBackgroundResource(R.drawable.banana_button);						
				}else if(pos==2){
					butt_fruit_3.setBackgroundResource(R.drawable.strawberry_button);						
				}else if(pos==3){
					butt_fruit_3.setBackgroundResource(R.drawable.cherry_button);								
				}else if(pos==4){
					butt_fruit_3.setBackgroundResource(R.drawable.pear_button);								
				}else{
					butt_fruit_3.setBackgroundResource(R.drawable.other_button);							
				}
			}
			@Override
		  	public void onNothingSelected(AdapterView<?> arg0) {
				butt_fruit_3.setBackgroundResource(R.drawable.strawberry_button);
			}	
		});
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
