// package com.mop.dictionaryApp;

// import java.io.Serializable;

// public class WordId implements Serializable{
//     private static final long serialVersionUID = 1L;
    
//     Integer wordid;
//     Integer synsetid;

//     public WordId(){}

//     public WordId(Integer word, Integer definition) {
//         this.wordid = word;
//         this.synsetid = definition;
//     }

//     public Integer getWordId() {
//         return this.wordid;
//     }

//     public void setWordId(Integer id) {
//         this.wordid = id;
//     }
    
//     public Integer getSynsetId() {
//         return this.synsetid;
//     }

//     public void setSynsetId(Integer id) {
//         this.synsetid = id;
//     }

//     @Override
//     public int hashCode() {
//         int hash = 0;
//         hash += (wordid != null ? wordid.hashCode() : 0);
//         return hash;
//     }

//     @Override
//     public boolean equals(Object object) {
//         if (!(object instanceof Word)) {
//             return false;
//         }
//         Word other = (Word) object;
//         if ((this.wordid == null && this.synsetid == null && other.wordid != null && other.synsetid != null) 
//         || ((this.wordid != null && this.synsetid != null) && !(this.wordid.equals(other.wordid) && this.synsetid.equals(other.wordid)))) {
//             return false;
//         }
//         return true;
//     }
// }
