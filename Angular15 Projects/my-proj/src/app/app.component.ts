import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-proj';
  inputValue: string | undefined;

  @ViewChild("myInput", { static: false }) myInput!: ElementRef;

  getValue() {
    const inputElement: HTMLInputElement = this.myInput.nativeElement;
    const value = inputElement.value;
    console.log(value);
  }
}
