using System.Collections.Generic;
using System.Linq;

namespace booking_classes
{
    public class Book
    {
        public string Title, Author, Publisher;
        public Book(string title, string author, string publisher)
        {
            Title = title;
            Author = author;
            Publisher = publisher;
        }

        public override string ToString()
        {
            return Title + " | " + Author + " | " + Publisher;
        }
    }

    class BookShelf
    {
        public delegate void bookSortDel();
        Book[] Books;

        public bookSortDel currentSorting;

        private void sortByTitle()
        {
            IEnumerable<Book> query = Books.OrderBy(x => x.Title);
            Books =  query.ToArray();
        }

        private void sortByAuthor()
        {
            IEnumerable<Book> query = Books.OrderBy(x => x.Author);
            Books = query.ToArray();
        }

        private void sortByPublisher()
        {
            IEnumerable<Book> query = Books.OrderBy(x => x.Publisher);
            Books = query.ToArray();
        }

        public void sortBy(string sortingMode)
        {
            bookSortDel curSorting = sortingMode switch
            {
                "title" => sortByTitle,
                "author" => sortByAuthor,
                "publisher" => sortByPublisher,
                _ => sortByTitle
            };
            curSorting();
        }


        public BookShelf(Book[] books)
        {
            Books = books;
        }

        public override string ToString()
        {
            string res = "";

            foreach (Book el in Books)
            {
                res += el.ToString() + "\n";
            }
            return res;
        }
    }
}
