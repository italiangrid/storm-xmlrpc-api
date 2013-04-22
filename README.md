StoRM-XMLRPC-API
================

StoRM XMLRPC-API provides java API for StoRM BackEnd's XMLRPC interface. All of the srm operations provided are synch calls.

## Supported platforms

* Scientific Linux 5 x86_64
* Scientific Linux 6 x86_64

## Building
Required packages:

* git
* java-1.6.0-openjdk
* java-1.6.0-openjdk-devel
* maven

> Maven is a Java tool, so you must have Java installed in order to proceed. More precisely, you need a Java Development Kit (JDK), the Java Runtime Environment (JRE) is not sufficient.

Download source files:
<pre>
git clone https://github.com/italiangrid/storm-xmlrpc-api.git
cd storm-xmlrpc-api
</pre>

Build commands:
<pre>
wget --no-check-certificate https://raw.github.com/italiangrid/build-settings/master/maven/cnaf-mirror-settings.xml -O mirror-settings.xml
mvn -s mirror-settings.xml clean
mvn -s mirror-settings.xml -U package
</pre>

It could be necessary to set JAVA_HOME environment variable, for example:
<pre>
export JAVA_HOME="/usr/lib/jvm/java"
</pre>

# Contact info

If you have problems, questions, ideas or suggestions, please contact us at
the following URLs

* GGUS (official support channel): http://www.ggus.eu
