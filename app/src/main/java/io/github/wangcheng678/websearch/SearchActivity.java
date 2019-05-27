package io.github.wangcheng678.websearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SearchActivity extends Activity {
  static private String engine = "https://google.com/search?q=";

  static Intent getSearchIntent(String query) {
    Uri urlFromQuery = Uri.parse(query);
    if(urlFromQuery.isAbsolute()){
      return new Intent(Intent.ACTION_VIEW, urlFromQuery);
    }
    Uri url = Uri.parse(engine + query);
    return new Intent(Intent.ACTION_VIEW, url);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    String action = intent.getAction();
    String query;
    if (Intent.ACTION_PROCESS_TEXT.equals(action)) {
      query = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
    } else if (Intent.ACTION_SEARCH.equals(action) || Intent.ACTION_WEB_SEARCH.equals(action)) {
      query = intent.getStringExtra(SearchManager.QUERY);
    } else {
      finish();
      return;
    }

    Intent resultIntent = getSearchIntent(query);
    if (resultIntent.resolveActivity(getPackageManager()) != null) {
      startActivity(resultIntent);
    }

    finish();
  }
}
