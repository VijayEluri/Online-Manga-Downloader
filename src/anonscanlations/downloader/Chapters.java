package anonscanlations.downloader;

/**
 *
 * @author /a/non <anonymousscanlations@gmail.com>
 */
public enum Chapters
{
    ACTIBOOK("Actibook"),
    COMICRUSH("Comic Rush (CLIP)"),
    CROCHETTIME("CrochetTime"),
    FLIPPER3("Flipper3"),
    MANGAONWEB("Manga On Web"),
    NICONICOACE("NicoNico E-Books"),
    NICONICO2("NicoNico Official"),
    NICONICO("NicoNico User-published"),
    PCVIEWER("PCViewer/DOR"),
    PLUGINFREE("PluginFree"),
    POCO("Poco"),
    CLUBSUNDAY("Shogakukan"),
    WEBYOUNGJUMP("Web Young Jump"),
    YAHOOBOOKSTORE("Yahoo Bookstore");

    private final String name;
    private Chapters(String _name)
    {
        name = _name;
    }
    public String getName()
    {
        return(name);
    }
    @Override
    public String toString()
    {
        return(name);
    }

    public static Chapters fromName(String name)
    {
        for(Chapters c : Chapters.class.getEnumConstants())
        {
            if(c.getName().equals(name))
                return(c);
        }
        return(null);
    }
}
