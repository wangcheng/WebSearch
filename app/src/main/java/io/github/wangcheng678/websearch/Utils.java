package io.github.wangcheng678.websearch;

import android.content.Intent;
import android.net.Uri;

class Utils {
  static private String engine = "https://google.com/search?q=";

  static Intent getSearchIntent(String query){
    Uri url = Uri.parse(engine + query);
    return new Intent(Intent.ACTION_VIEW, url);
  }
}
