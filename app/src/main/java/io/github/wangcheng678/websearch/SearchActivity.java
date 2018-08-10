package io.github.wangcheng678.websearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SearchActivity extends Activity {
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

    Intent resultIntent = Utils.getSearchIntent(query);
    if (resultIntent.resolveActivity(getPackageManager()) != null) {
      startActivity(resultIntent);
    }

    finish();
  }
}
