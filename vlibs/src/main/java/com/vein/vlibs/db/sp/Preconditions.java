package com.vein.vlibs.db.sp;

final class Preconditions {
  static void checkNotNull(Object o, String message) {
    if (o == null) {
      throw new NullPointerException(message);
    }
  }

  private Preconditions() {
    throw new AssertionError("No instances");
  }
}
