package dev.tberghuis.btmacrokb

import dev.tberghuis.btmacrokb.util.byteArrayOfInts

const val KEYBOARD_ID = 8

// is there a way to run `byteArrayOfInts()` at compile time??? ksp
val asciiCharToReportByteArray = mapOf(
  'a' to byteArrayOfInts(0x0, 0x0, 0x4, 0x0, 0x0, 0x0, 0x0, 0x0),
  'b' to byteArrayOfInts(0x0, 0x0, 0x5, 0x0, 0x0, 0x0, 0x0, 0x0),
  'c' to byteArrayOfInts(0x0, 0x0, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0),
  'd' to byteArrayOfInts(0x0, 0x0, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0),
  'e' to byteArrayOfInts(0x0, 0x0, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0),
  'f' to byteArrayOfInts(0x0, 0x0, 0x9, 0x0, 0x0, 0x0, 0x0, 0x0),
  'g' to byteArrayOfInts(0x0, 0x0, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0),
  'h' to byteArrayOfInts(0x0, 0x0, 0xb, 0x0, 0x0, 0x0, 0x0, 0x0),
  'i' to byteArrayOfInts(0x0, 0x0, 0xc, 0x0, 0x0, 0x0, 0x0, 0x0),
  'j' to byteArrayOfInts(0x0, 0x0, 0xd, 0x0, 0x0, 0x0, 0x0, 0x0),
  'k' to byteArrayOfInts(0x0, 0x0, 0xe, 0x0, 0x0, 0x0, 0x0, 0x0),
  'l' to byteArrayOfInts(0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0x0, 0x0),
  'm' to byteArrayOfInts(0x0, 0x0, 0x10, 0x0, 0x0, 0x0, 0x0, 0x0),
  'n' to byteArrayOfInts(0x0, 0x0, 0x11, 0x0, 0x0, 0x0, 0x0, 0x0),
  'o' to byteArrayOfInts(0x0, 0x0, 0x12, 0x0, 0x0, 0x0, 0x0, 0x0),
  'p' to byteArrayOfInts(0x0, 0x0, 0x13, 0x0, 0x0, 0x0, 0x0, 0x0),
  'q' to byteArrayOfInts(0x0, 0x0, 0x14, 0x0, 0x0, 0x0, 0x0, 0x0),
  'r' to byteArrayOfInts(0x0, 0x0, 0x15, 0x0, 0x0, 0x0, 0x0, 0x0),
  's' to byteArrayOfInts(0x0, 0x0, 0x16, 0x0, 0x0, 0x0, 0x0, 0x0),
  't' to byteArrayOfInts(0x0, 0x0, 0x17, 0x0, 0x0, 0x0, 0x0, 0x0),
  'u' to byteArrayOfInts(0x0, 0x0, 0x18, 0x0, 0x0, 0x0, 0x0, 0x0),
  'v' to byteArrayOfInts(0x0, 0x0, 0x19, 0x0, 0x0, 0x0, 0x0, 0x0),
  'w' to byteArrayOfInts(0x0, 0x0, 0x1a, 0x0, 0x0, 0x0, 0x0, 0x0),
  'x' to byteArrayOfInts(0x0, 0x0, 0x1b, 0x0, 0x0, 0x0, 0x0, 0x0),
  'y' to byteArrayOfInts(0x0, 0x0, 0x1c, 0x0, 0x0, 0x0, 0x0, 0x0),
  'z' to byteArrayOfInts(0x0, 0x0, 0x1d, 0x0, 0x0, 0x0, 0x0, 0x0),

  'A' to byteArrayOfInts(0x2, 0x0, 0x4, 0x0, 0x0, 0x0, 0x0, 0x0),
  'B' to byteArrayOfInts(0x2, 0x0, 0x5, 0x0, 0x0, 0x0, 0x0, 0x0),
  'C' to byteArrayOfInts(0x2, 0x0, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0),
  'D' to byteArrayOfInts(0x2, 0x0, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0),
  'E' to byteArrayOfInts(0x2, 0x0, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0),
  'F' to byteArrayOfInts(0x2, 0x0, 0x9, 0x0, 0x0, 0x0, 0x0, 0x0),
  'G' to byteArrayOfInts(0x2, 0x0, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0),
  'H' to byteArrayOfInts(0x2, 0x0, 0xb, 0x0, 0x0, 0x0, 0x0, 0x0),
  'I' to byteArrayOfInts(0x2, 0x0, 0xc, 0x0, 0x0, 0x0, 0x0, 0x0),
  'J' to byteArrayOfInts(0x2, 0x0, 0xd, 0x0, 0x0, 0x0, 0x0, 0x0),
  'K' to byteArrayOfInts(0x2, 0x0, 0xe, 0x0, 0x0, 0x0, 0x0, 0x0),
  'L' to byteArrayOfInts(0x2, 0x0, 0xf, 0x0, 0x0, 0x0, 0x0, 0x0),
  'M' to byteArrayOfInts(0x2, 0x0, 0x10, 0x0, 0x0, 0x0, 0x0, 0x0),
  'N' to byteArrayOfInts(0x2, 0x0, 0x11, 0x0, 0x0, 0x0, 0x0, 0x0),
  'O' to byteArrayOfInts(0x2, 0x0, 0x12, 0x0, 0x0, 0x0, 0x0, 0x0),
  'P' to byteArrayOfInts(0x2, 0x0, 0x13, 0x0, 0x0, 0x0, 0x0, 0x0),
  'Q' to byteArrayOfInts(0x2, 0x0, 0x14, 0x0, 0x0, 0x0, 0x0, 0x0),
  'R' to byteArrayOfInts(0x2, 0x0, 0x15, 0x0, 0x0, 0x0, 0x0, 0x0),
  'S' to byteArrayOfInts(0x2, 0x0, 0x16, 0x0, 0x0, 0x0, 0x0, 0x0),
  'T' to byteArrayOfInts(0x2, 0x0, 0x17, 0x0, 0x0, 0x0, 0x0, 0x0),
  'U' to byteArrayOfInts(0x2, 0x0, 0x18, 0x0, 0x0, 0x0, 0x0, 0x0),
  'V' to byteArrayOfInts(0x2, 0x0, 0x19, 0x0, 0x0, 0x0, 0x0, 0x0),
  'W' to byteArrayOfInts(0x2, 0x0, 0x1a, 0x0, 0x0, 0x0, 0x0, 0x0),
  'X' to byteArrayOfInts(0x2, 0x0, 0x1b, 0x0, 0x0, 0x0, 0x0, 0x0),
  'Y' to byteArrayOfInts(0x2, 0x0, 0x1c, 0x0, 0x0, 0x0, 0x0, 0x0),
  'Z' to byteArrayOfInts(0x2, 0x0, 0x1d, 0x0, 0x0, 0x0, 0x0, 0x0),

  '\n' to byteArrayOfInts(0x0, 0x0, 0x28, 0x0, 0x0, 0x0, 0x0, 0x0),
  '\t' to byteArrayOfInts(0x0, 0x0, 0x2b, 0x0, 0x0, 0x0, 0x0, 0x0),
  ' ' to byteArrayOfInts(0x0, 0x0, 0x2c, 0x0, 0x0, 0x0, 0x0, 0x0),

  '1' to byteArrayOfInts(0x0, 0x0, 0x1e, 0x0, 0x0, 0x0, 0x0, 0x0),
  '2' to byteArrayOfInts(0x0, 0x0, 0x1f, 0x0, 0x0, 0x0, 0x0, 0x0),
  '3' to byteArrayOfInts(0x0, 0x0, 0x20, 0x0, 0x0, 0x0, 0x0, 0x0),
  '4' to byteArrayOfInts(0x0, 0x0, 0x21, 0x0, 0x0, 0x0, 0x0, 0x0),
  '5' to byteArrayOfInts(0x0, 0x0, 0x22, 0x0, 0x0, 0x0, 0x0, 0x0),
  '6' to byteArrayOfInts(0x0, 0x0, 0x23, 0x0, 0x0, 0x0, 0x0, 0x0),
  '7' to byteArrayOfInts(0x0, 0x0, 0x24, 0x0, 0x0, 0x0, 0x0, 0x0),
  '8' to byteArrayOfInts(0x0, 0x0, 0x25, 0x0, 0x0, 0x0, 0x0, 0x0),
  '9' to byteArrayOfInts(0x0, 0x0, 0x26, 0x0, 0x0, 0x0, 0x0, 0x0),
  '0' to byteArrayOfInts(0x0, 0x0, 0x27, 0x0, 0x0, 0x0, 0x0, 0x0),

  '`' to byteArrayOfInts(0x0, 0x0, 0x35, 0x0, 0x0, 0x0, 0x0, 0x0),

  '-' to byteArrayOfInts(0x0, 0x0, 0x2d, 0x0, 0x0, 0x0, 0x0, 0x0),
  '=' to byteArrayOfInts(0x0, 0x0, 0x2e, 0x0, 0x0, 0x0, 0x0, 0x0),

  '[' to byteArrayOfInts(0x0, 0x0, 0x2f, 0x0, 0x0, 0x0, 0x0, 0x0),
  ']' to byteArrayOfInts(0x0, 0x0, 0x30, 0x0, 0x0, 0x0, 0x0, 0x0),
  '\\' to byteArrayOfInts(0x0, 0x0, 0x31, 0x0, 0x0, 0x0, 0x0, 0x0),


  ';' to byteArrayOfInts(0x0, 0x0, 0x33, 0x0, 0x0, 0x0, 0x0, 0x0),
  '\'' to byteArrayOfInts(0x0, 0x0, 0x34, 0x0, 0x0, 0x0, 0x0, 0x0),
  ',' to byteArrayOfInts(0x0, 0x0, 0x36, 0x0, 0x0, 0x0, 0x0, 0x0),
  '.' to byteArrayOfInts(0x0, 0x0, 0x37, 0x0, 0x0, 0x0, 0x0, 0x0),
  '/' to byteArrayOfInts(0x0, 0x0, 0x38, 0x0, 0x0, 0x0, 0x0, 0x0),

  '~' to byteArrayOfInts(0x2, 0x0, 0x35, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + `

  '!' to byteArrayOfInts(0x2, 0x0, 0x1e, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + 1
  '@' to byteArrayOfInts(0x2, 0x0, 0x1f, 0x0, 0x0, 0x0, 0x0, 0x0),
  '#' to byteArrayOfInts(0x2, 0x0, 0x20, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + 3
  '$' to byteArrayOfInts(0x2, 0x0, 0x21, 0x0, 0x0, 0x0, 0x0, 0x0),
  '%' to byteArrayOfInts(0x2, 0x0, 0x22, 0x0, 0x0, 0x0, 0x0, 0x0),
  '^' to byteArrayOfInts(0x2, 0x0, 0x23, 0x0, 0x0, 0x0, 0x0, 0x0),
  '&' to byteArrayOfInts(0x2, 0x0, 0x24, 0x0, 0x0, 0x0, 0x0, 0x0),
  '*' to byteArrayOfInts(0x2, 0x0, 0x25, 0x0, 0x0, 0x0, 0x0, 0x0),
  '(' to byteArrayOfInts(0x2, 0x0, 0x26, 0x0, 0x0, 0x0, 0x0, 0x0),
  ')' to byteArrayOfInts(0x2, 0x0, 0x27, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + 0

  '_' to byteArrayOfInts(0x2, 0x0, 0x2d, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + -
  '+' to byteArrayOfInts(0x2, 0x0, 0x2e, 0x0, 0x0, 0x0, 0x0, 0x0), // shift + +

  '{' to byteArrayOfInts(0x2, 0x0, 0x2f, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  '}' to byteArrayOfInts(0x2, 0x0, 0x30, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  '|' to byteArrayOfInts(0x2, 0x0, 0x31, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  ':' to byteArrayOfInts(0x2, 0x0, 0x33, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  '"' to byteArrayOfInts(0x2, 0x0, 0x34, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +

  '<' to byteArrayOfInts(0x2, 0x0, 0x36, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  '>' to byteArrayOfInts(0x2, 0x0, 0x37, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
  '?' to byteArrayOfInts(0x2, 0x0, 0x38, 0x0, 0x0, 0x0, 0x0, 0x0), // shift +
)

