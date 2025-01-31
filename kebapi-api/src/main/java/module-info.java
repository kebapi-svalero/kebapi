module org.kebapi.kebapi {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.core;
  requires spring.beans;
  requires spring.data.jpa;
  requires jakarta.persistence;
  requires java.sql;
  requires static lombok;
  requires modelmapper;

  requires spring.web;
  requires spring.data.commons;
  requires org.slf4j;
  opens org.kebapi.kebapi to spring.core;
  exports org.kebapi.kebapi;
}