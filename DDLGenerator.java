import netseenergy.bd.HibernateUtil;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;

public class DDLGenerator {

  public static void main(String args[]) {

    gerar();
  }


  private static void gerar() {
    new SchemaExport(HibernateUtil.getCfg())
        .setOutputFile("/Users/mtoure/dev/poc/schema-all.sql")
        .setFormat(true)
        .setDelimiter(";")
        .create(Target.EXPORT);
  }
}
