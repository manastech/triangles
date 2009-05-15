using System;
using System.Collections.Generic;
using System.Linq;

namespace Triangles
{
	class Triangles
	{
		static void Main(string[] args)
		{
			var sides = new string[]
			{
				"ABD", "BCE", "ACF", "DFG", "DEH", "EFI",
				"AEGJ", "BFHJ", "CDIJ"
			};

			var edges = sides.SelectMany(s => GenerateEdges(s)).ToArray();

			var triangles = from x in edges
							from y in edges
							from z in edges
							where x[1] == y[0] && y[1] == z[1] && z[0] == x[0]
							let t = new[] { x[0], x[1], y[1] }
							where !sides.Any(s => t.All(v => s.Contains(v)))
							select new string(t);

			foreach (var t in triangles.OrderBy(x => x))
				Console.WriteLine(t);

			Console.WriteLine("Total: {0} triangles", triangles.Count());
		}

		private static IEnumerable<string> GenerateEdges(string x)
		{
			for (int i = 0; i < x.Length; i++)
				for (int j = i + 1; j < x.Length; j++)
					yield return new string(new[] { x[i], x[j] });
		}
	}
}
