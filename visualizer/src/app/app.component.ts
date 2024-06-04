import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'Visualizer';
  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit() {
    this.primengConfig.ripple = true;
    this.primengConfig.setTranslation({
      accept: 'Si',
      addRule: 'Aggiungi regola',
      am: 'AM',
      apply: 'Applica',
      cancel: 'Annulla',
      choose: 'Scegli',
      chooseDate: 'Seleziona Data',
      chooseMonth: 'Seleziona Mese',
      chooseYear: 'Seleziona Anno',
      clear: 'Cancella tutto',
      contains: 'Contiene',
      dateAfter: 'La data è successiva',
      dateBefore: 'La data è precedente',
      dateFormat: 'dd/mm/yy',
      dateIs: 'La data è',
      dateIsNot: 'La data non è',
      dayNames: [
        'Domenica',
        'Lunedi',
        'Martedì',
        'Mercoledì',
        'Giovedì',
        'Venerdì',
        'Sabato',
      ],
      dayNamesMin: ['Do', 'Lu', 'Ma', 'Me', 'Gi', 'Ve', 'Sa'],
      dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mer', 'Gio', 'Ven', 'Sab'],
      emptyFilterMessage: 'Nessuna opzione disponibile',
      emptyMessage: 'Nessun risultato trovato',
      emptySearchMessage: 'Nessun risultato trovato',
      emptySelectionMessage: 'Nessun elemento selezionato',
      endsWith: 'Finisce con',
      equals: 'Equivale',
      fileSizeTypes: ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
      firstDayOfWeek: 1,
      gt: 'Maggiore di',
      gte: 'Maggiore o uguale a',
      lt: 'Minore di',
      lte: 'Minore o uguale a',
      matchAll: 'Abbina tutto',
      matchAny: 'Abbina alcuni',
      medium: 'Medio',
      monthNames: [
        'Gennaio',
        'Febbraio',
        'Marzo',
        'Aprile',
        'Maggio',
        'Giugno',
        'Luglio',
        'Agosto',
        'Settembre',
        'Ottobre',
        'Novembre',
        'Dicembre',
      ],
      monthNamesShort: [
        'Gen',
        'Feb',
        'Mar',
        'Apr',
        'Mag',
        'Giu',
        'Lug',
        'Ago',
        'Set',
        'Ott',
        'Nov',
        'Dic',
      ],
      nextDecade: 'Decade successiva',
      nextHour: 'Ora successiva',
      nextMinute: 'Minuto successivo',
      nextMonth: 'Mese successivo',
      nextSecond: 'Secondo successivo',
      nextYear: 'Anno successivo',
      noFilter: 'Senza Filtro',
      notContains: 'Non contiene',
      notEquals: 'Non uguale',
      passwordPrompt: 'Inserisci la password',
      pending: 'In corso',
      pm: 'PM',
      prevDecade: 'Decade precedente',
      prevHour: 'Ora precedente',
      prevMinute: 'Minuto precedente',
      prevMonth: 'Mese precedente',
      prevSecond: 'Secondo precedente',
      prevYear: 'Anno precedente',
      reject: 'No',
      removeRule: 'Rimuovi regola',
      searchMessage: '{0} risultati disponibili',
      selectionMessage: '{0} elementi selezionati',
      startsWith: 'Inizia con',
      strong: 'Forte',
      today: 'Oggi',
      upload: 'Carica',
      weak: 'Debole',
      weekHeader: 'Sett',
      aria: {
        cancelEdit: 'Annulla modifica',
        close: 'Chiudi',
        collapseRow: 'Riduci riga',
        editRow: 'Modifica riga',
        expandRow: 'Espandi riga',
        falseLabel: 'Falso',
        filterConstraint: 'Costante di filtro',
        filterOperator: 'Operatore di filtro',
        firstPageLabel: 'Prima pagina',
        gridView: 'Griglia',
        hideFilterMenu: 'Nascondi Menu filtri',
        jumpToPageDropdownLabel: 'Vai alla Dropdown delle pagine',
        jumpToPageInputLabel: "Vai all'Input delle pagine",
        lastPageLabel: 'Ultima pagina',
        listView: 'Lista',
        moveAllToSource: 'Muovi tutto alla sorgente',
        moveAllToTarget: "Muovi tutto all'elemento",
        moveBottom: 'Vai in fondo',
        moveDown: 'Vai sotto',
        moveTop: 'Vai in cima',
        moveToSource: 'Vai alla sorgente',
        moveToTarget: "Vai all'elemento",
        moveUp: 'Vai sopra',
        navigation: 'Naviga',
        next: 'Successivo',
        nextPageLabel: 'Pagina successiva',
        nullLabel: 'Non selezionato',
        pageLabel: 'Pagina {page}',
        previous: 'Precedente',
        previousPageLabel: 'Pagina precedente',
        rotateLeft: 'Ruota a sinistra',
        rotateRight: 'Ruota a destra',
        rowsPerPageLabel: 'Elementi per pagina',
        saveEdit: 'Salva modifica',
        scrollTop: 'Torna sù',
        selectAll: 'Seleziona tutti gli elementi',
        selectRow: 'Seleziona riga',
        showFilterMenu: 'Mostra Menu filtri',
        slide: 'Scorri',
        slideNumber: '{slideNumber}',
        star: '1 stella',
        stars: '{star} stelle',
        trueLabel: 'Vero',
        unselectAll: 'Deseleziona tutti gli elementi',
        unselectRow: 'Deseleziona riga',
        zoomImage: 'Zoom Immagine',
        zoomIn: 'Ingrandisci',
        zoomOut: 'Riduci',
      },
    });
  }
}
