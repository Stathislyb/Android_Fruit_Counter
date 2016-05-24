package uowm.st0186.finalexams;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	String name,aem;
	Button butt_start,butt_exit;
	EditText name_field,aem_field;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name_field = (EditText) findViewById(R.id.name_field);
		aem_field = (EditText) findViewById(R.id.AEM_field);
		
		
		butt_exit = (Button) findViewById(R.id.Exit_button);
		butt_exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		
		
		butt_start = (Button) findViewById(R.id.Start_button);
		butt_start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = name_field.getText().toString();
				String aem = aem_field.getText().toString();
				if(name.trim().length() == 0 || aem.trim().length() == 0){
					Toast.makeText(getApplicationContext(), "Please give a name and AEM.",
						   Toast.LENGTH_LONG).show();
				}else if(!isNumeric(aem)){
					Toast.makeText(getApplicationContext(), "AEM can only be numerical.",
							   Toast.LENGTH_LONG).show();
				}else{
					Intent intent_results = new Intent(MainActivity.this, InputsActivity.class);
					Bundle extras = new Bundle();
					extras.putString("name",name);
					extras.putString("aem",aem);
					intent_results.putExtras(extras);
					startActivity(intent_results);
					MainActivity.this.finish();
				}	
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
	
	public static boolean isNumeric(String str){
	    for (char c : str.toCharArray()){
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
}
