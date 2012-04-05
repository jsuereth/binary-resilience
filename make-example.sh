#!/bin/bash

if test "$1" == ""; then
  echo Must supply an example name.
  exit 1
fi

base=$(dirname $0)

mkdir -p ${base}/$1/src/main/scala
mkdir -p ${base}/$1/src/main/scala1


cat - >${base}/${1}/src/main/scala/first.scala <<EOF

object Main {
  def main(args: Array[String]): Unit = ()
}
EOF


gedit "${base}/$1/src/main/scala/first.scala" "${base}/$1/src/main/scala1/second.scala" 

