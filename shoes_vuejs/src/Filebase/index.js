import { initializeApp } from "firebase/app";
import { getFirestore, collection } from "firebase/firestore";
// ... other firebase imports

export const firebaseApp = initializeApp({
  apiKey: "AIzaSyCskE16aKt5ZmAjhU1HCGCR8toarWhUo5M",
  authDomain: "demo01-9acba.firebaseapp.com",
  databaseURL: "https://demo01-9acba-default-rtdb.firebaseio.com",
  projectId: "demo01-9acba",
  storageBucket: "demo01-9acba.appspot.com",
  messagingSenderId: "851189434663",
  appId: "1:851189434663:web:cb25031554e92700e8c102",
  measurementId: "G-RPJVR0NVPB",
});

// used for the firestore refs
const db = getFirestore(firebaseApp);

// here we can export reusable database references
export const todosRef = collection(db, "todos");

export default { firebaseApp };
