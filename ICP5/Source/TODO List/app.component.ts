import { Component } from '@angular/core';
import { FormGroup} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // define list of items
  items= [];
  compltedItems= [];
  // Write code to push new item
  submitNewItem(event) {
      this.items.push(event.taskName);
  }

  // Write code to complete item
  completeItem(item: string) {
    const index: number = this.items.indexOf(item);
    if (index !== -1) {
      this.items.splice(index, 1);
      this.compltedItems.push(item);
    }
  }

  // Write code to delete item
  deleteItem(item:string) {
    let index: number = this.items.indexOf(item);
    if (index !== -1) {
      this.items.splice(index, 1);
    } else {
      index = this.compltedItems.indexOf(item);
      if (index !== -1) {
        this.compltedItems.splice(index, 1);
      }
    }
  }

}
